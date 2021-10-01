package demo.app.paintball.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.config.Config
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.ble.BleService
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.mqtt.messages.PositionMessage
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.fragments.buttons.MapButtonsFragment
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.panels.MapStatsPanelFragment
import demo.app.paintball.map.MapView
import demo.app.paintball.map.rendering.MapViewImpl
import demo.app.paintball.map.sensors.GestureSensor
import demo.app.paintball.map.sensors.Gyroscope
import demo.app.paintball.util.*
import demo.app.paintball.util.positioning.PositionCalculator
import demo.app.paintball.util.positioning.PositionCalculatorImpl
import kotlinx.android.synthetic.main.activity_map.*
import retrofit2.Response
import javax.inject.Inject

class MapActivity : AppCompatActivity(), GestureSensor.GestureListener, Gyroscope.GyroscopeListener,
    RestService.SuccessListener, ConnectTagFragment.ConnectTagListener,
    MqttService.PositionListener, MapViewImpl.MapViewCreatedListener, BleServiceImpl.BleServiceListener,
    PositionCalculator.PositionCalculatorListener, MqttService.GameListener {

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    @Inject
    lateinit var bleService: BleService

    private var game: Game? = null
    private var isMapButtonsOpen = false

    private lateinit var map: MapView
    private lateinit var mainButtons: MapButtonsFragment
    private lateinit var chatButtons: MapButtonsFragment

    private lateinit var statsPanel: MapStatsPanelFragment

    private lateinit var gyroscope: Gyroscope

    private lateinit var positionCalculator: PositionCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        map = mapView
        mainButtons = supportFragmentManager.findFragmentById(R.id.mainButtonsFragment) as MapButtonsFragment
        chatButtons = supportFragmentManager.findFragmentById(R.id.chatButtonsFragment) as MapButtonsFragment

        statsPanel = supportFragmentManager.findFragmentById(R.id.statsPanelFragment) as MapStatsPanelFragment

        mainButtons.initLevel(0)
        chatButtons.initLevel(1)

        map.setOnTouchListener(GestureSensor(gestureListener = this, scrollPanel = buttonsPanel))
        gyroscope = Gyroscope(gyroscopeListener = this)

        restService = services.rest().apply { listener = this@MapActivity; errorListener = ErrorHandler }
        bleService = services.ble().also { it.addListener(this@MapActivity) }
        mqttService = services.mqtt().apply { positionListener = this@MapActivity; gameListener = this@MapActivity }

        if (!resources.getBoolean(R.bool.mapOnlyMode)) {
            restService.getGame()
            bleService.startPositionSending()
            mqttService.subscribe(playerTopics.teamChat)
            mqttService.subscribe(playerTopics.positions)
        }

        fabActivateButtons.setOnClickListener {
            if (isMapButtonsOpen) hideButtons() else showButtons()
        }

        positionCalculator = PositionCalculatorImpl(Config.mapConfig.anchors).apply { listener = this@MapActivity }

        if (resources.getBoolean(R.bool.mapOnlyMode) && resources.getBoolean(R.bool.tagsEnabled)) {
            ConnectTagFragment().show(supportFragmentManager, "TAG")
        }
    }

    override fun onResume() {
        super.onResume()
        gyroscope.start()
    }

    override fun onPause() {
        super.onPause()
        gyroscope.stop()
    }

    override fun mapViewCreated() {
        if (resources.getBoolean(R.bool.displayAnchors)) {
            Config.mapConfig.anchors
                .filter { it[2] != 0 }
                .forEach { map.addAnchor(it[0], it[1]) }
        }
    }

    override fun onBackPressed() {
    }

    override fun onScaleChanged(scaleFactor: Float) {
        map.zoom(scaleFactor)
    }

    override fun onZoomIn() {
        hideButtons()
    }

    override fun onZoomOut() {
        statsPanel.show()
    }

    override fun onScrollUp() {
        mainButtons.changeLevel(-1)
        chatButtons.changeLevel(0)
        btnPagingChat.setBackgroundTint(R.color.transparentWhite)
        btnPagingMain.setBackgroundTint(R.color.lightTransparentGray)
    }

    override fun onScrollDown() {
        mainButtons.changeLevel(0)
        chatButtons.changeLevel(1)
        btnPagingChat.setBackgroundTint(R.color.lightTransparentGray)
        btnPagingMain.setBackgroundTint(R.color.transparentWhite)
    }

    override fun onOrientationChanged(radian: Float) {
        map.setPlayerOrientation(radian.toDegree())
    }

    override fun getGameSuccess(response: Response<Game>) {
        game = response.body()
        statsPanel.refresh(game = response.body())
        addPlayersToMap()
    }

    private fun addPlayersToMap() {
        game?.redTeam
            ?.filter { it.name != player.name }
            ?.forEach { map.addPlayer(it) }
        game?.blueTeam
            ?.filter { it.name != player.name }
            ?.forEach { map.addPlayer(it) }
    }

    override fun createGameSuccess() {
    }

    override fun addRedPlayerSuccess() {
    }

    override fun addBluePlayerSuccess() {
    }

    override fun onTagConnected() {
        bleService.startPositionSending()
    }

    override fun positionMessageArrived(message: PositionMessage) {
        map.setMovablePosition(message.playerName, message.posX, message.posY)
    }

    override fun connectComplete() {
        mqttService.subscribe(playerTopics.teamChat)
        mqttService.subscribe(playerTopics.positions)
    }

    override fun gameMessageArrived(message: GameMessage) {
        game?.let {
            if (message.type == GameMessage.Type.LEAVE) {
                it.leave(message.playerName)
                statsPanel.refresh(it)
                map.removePlayer(message.playerName)
                toast(getString(R.string.player_left_the_game, message.playerName))
            }
        }
    }

    override fun onBleConnected(connection: BleService) {
        bleService.startPositionSending()
    }

    override fun onBlePositionDataReceived(connection: BleService, data: BlePositionData) {
        positionCalculator.calculate(data)
    }

    override fun onBleDisconnected(connection: BleService) {
        toast("Tag disconnected")
    }

    override fun onPositionCalculated(posX: Int, posY: Int) {
        map.setPlayerPosition(posX, posY)
        PositionMessage(posX, posY).publish(mqttService)
    }

    private fun showButtons() {
        isMapButtonsOpen = true
        fabActivateButtons.setSrc(R.drawable.ic_unfold_less)
        fabActivateButtons.animate().rotation(180F)
        buttonsPagingLayout.animate().translationX(-50F)

        statsPanel.show()
        mainButtons.show()
        chatButtons.show()
    }

    private fun hideButtons() {
        isMapButtonsOpen = false
        fabActivateButtons.setSrc(R.drawable.ic_unfold_more)
        fabActivateButtons.animate().rotation(-180F)
        buttonsPagingLayout.animate().translationX(0F)

        statsPanel.hide()
        mainButtons.hide()
        chatButtons.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        bleService.removeListener(this)
        bleService.disconnectDevice()
    }
}