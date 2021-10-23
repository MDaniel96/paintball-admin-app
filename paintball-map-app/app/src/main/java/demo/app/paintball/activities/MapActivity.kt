package demo.app.paintball.activities

import android.location.Location
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import demo.app.paintball.PaintballApplication.Companion.currentUser
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.ble.BleService
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.mqtt.messages.PositionMessage
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.fragments.buttons.MapButtonsFragment
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.panels.MapStatsPanelFragment
import demo.app.paintball.map.MapView
import demo.app.paintball.map.sensors.GestureSensor
import demo.app.paintball.map.sensors.Gyroscope
import demo.app.paintball.map.sensors.Locator
import demo.app.paintball.positioning.calculators.PositionCalculatorListener
import demo.app.paintball.positioning.calculators.gps.GpsPositionCalculator
import demo.app.paintball.positioning.calculators.gps.GpsPositionCalculatorImpl
import demo.app.paintball.positioning.calculators.uwb.UwbPositionCalculator
import demo.app.paintball.positioning.calculators.uwb.UwbPositionCalculatorImpl
import demo.app.paintball.positioning.converters.PositionConverter
import demo.app.paintball.util.*
import kotlinx.android.synthetic.main.activity_map.*
import javax.inject.Inject

class MapActivity : AppCompatActivity(), GestureSensor.GestureListener, Gyroscope.GyroscopeListener,
    RestService.SuccessListener, ConnectTagFragment.ConnectTagListener,
    MqttService.PositionListener, BleServiceImpl.BleServiceListener,
    PositionCalculatorListener, MqttService.GameListener, Locator.LocatorListener {

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    @Inject
    lateinit var bleService: BleService

    private lateinit var game: Game

    private var isMapButtonsOpen = false

    private lateinit var mapViewElement: MapView
    private lateinit var mainButtons: MapButtonsFragment
    private lateinit var chatButtons: MapButtonsFragment

    private lateinit var statsPanel: MapStatsPanelFragment

    private lateinit var gyroscope: Gyroscope
    private lateinit var locator: Locator

    private lateinit var uwbPositionCalculator: UwbPositionCalculator
    private lateinit var gpsPositionCalculator: GpsPositionCalculator

    private lateinit var positionConverter: PositionConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mapViewElement = mapView
        mainButtons = supportFragmentManager.findFragmentById(R.id.mainButtonsFragment) as MapButtonsFragment
        chatButtons = supportFragmentManager.findFragmentById(R.id.chatButtonsFragment) as MapButtonsFragment

        statsPanel = supportFragmentManager.findFragmentById(R.id.statsPanelFragment) as MapStatsPanelFragment

        mainButtons.initLevel(0)
        chatButtons.initLevel(1)

        mapViewElement.setOnTouchListener(GestureSensor(gestureListener = this, scrollPanel = buttonsPanel))
        gyroscope = Gyroscope(gyroscopeListener = this)

        restService = services.rest().apply { listener = this@MapActivity; errorListener = ErrorHandler }
        bleService = services.ble().also { it.addListener(this@MapActivity) }
        mqttService = services.mqtt().apply { positionListener = this@MapActivity; gameListener = this@MapActivity }

        val selectedGameId = intent.getLongExtra("SELECTED_GAME_ID", -1L)
        restService.getGame(selectedGameId)
        bleService.startPositionSending()
        mqttService.subscribe(playerTopics.teamChat)
        mqttService.subscribe(playerTopics.positions)

        fabActivateButtons.setOnClickListener {
            if (isMapButtonsOpen) hideButtons() else showButtons()
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

    override fun onBackPressed() {
    }

    override fun onScaleChanged(scaleFactor: Float) {
        mapViewElement.zoom(scaleFactor)
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
        mapViewElement.setPlayerOrientation(radian.toDegree())
    }

    override fun onGetGame(game: Game) {
        this.game = game
        positionConverter = PositionConverter.create(game)
        mapViewElement.initMap(game.map!!)
        statsPanel.refresh(game)
        addUsersToMap()
        addAnchorsToMap(game)
        initPositionCalculators(game)
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    override fun onGetUsers(users: List<User>) {
    }

    override fun onAddUserToTeam(team: Team) {
    }

    private fun addUsersToMap() {
        game.redPlayers
            .filter { it.id != currentUser.id }
            .forEach { mapViewElement.addUser(it, Team.RED) }
        game.bluePlayers
            .filter { it.id != currentUser.id }
            .forEach { mapViewElement.addUser(it, Team.BLUE) }
    }

    private fun addAnchorsToMap(game: Game) {
        if (resources.getBoolean(R.bool.displayAnchors)) {
            game.map?.anchors?.forEach {
                mapViewElement.addAnchor(
                    positionConverter.mmToPx(it.x.toInt()),
                    positionConverter.mmToPx(it.y.toInt())
                )
            }
        }
    }

    private fun initPositionCalculators(game: Game) {
        when (game.localizationMode) {
            Game.LocalizationMode.UWB -> {
                val anchorPositions = game.map!!.anchors.map { intArrayOf(it.x.toInt(), it.y.toInt(), 1000) }
                uwbPositionCalculator = UwbPositionCalculatorImpl(anchorPositions).apply { listener = this@MapActivity }
            }
            Game.LocalizationMode.GPS -> {
                locator = Locator(listener = this)
                val originLocation = Location("").apply {
                    latitude = game.map!!.topLeftLatitude
                    longitude = game.map!!.topLeftLongitude
                }
                gpsPositionCalculator = GpsPositionCalculatorImpl(originLocation).apply { listener = this@MapActivity }
            }
        }
    }

    override fun onTagConnected() {
        bleService.startPositionSending()
    }

    override fun positionMessageArrived(message: PositionMessage) {
        game.map?.let {
            mapViewElement.setMovablePosition(
                message.playerName,
                positionConverter.mmToPx(message.posX),
                positionConverter.mmToPx(message.posX)
            )
        }
    }

    override fun connectComplete() {
        mqttService.subscribe(playerTopics.teamChat)
        mqttService.subscribe(playerTopics.positions)
    }

    override fun gameMessageArrived(message: GameMessage) {
        if (message.type == GameMessage.Type.LEAVE) {
            game.bluePlayers
                .union(game.redPlayers)
                .find { it.username == message.playerName }
                ?.let { leavingUser ->
                    game.deadPlayers.add(leavingUser)
                    statsPanel.refresh(game)
                    mapViewElement.removeUser(message.playerName)
                    toast(getString(R.string.player_left_the_game, message.playerName))
                }
        }
    }

    override fun onBleConnected(connection: BleService) {
        bleService.startPositionSending()
    }

    override fun onBlePositionDataReceived(connection: BleService, data: BlePositionData) {
        uwbPositionCalculator.calculate(data)
    }

    override fun onBleDisconnected(connection: BleService) {
        toast("Tag disconnected")
    }

    override fun onLocationChanged(location: Location) {
        gpsPositionCalculator.calculate(location)
    }

    override fun onPositionCalculated(posX: Int, posY: Int) {
        game.map?.let {
            mapViewElement.setPlayerPosition(positionConverter.mmToPx(posX), positionConverter.mmToPx(posY))
        }
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
        locator.cancel()
    }
}