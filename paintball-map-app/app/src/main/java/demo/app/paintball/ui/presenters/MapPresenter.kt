package demo.app.paintball.ui.presenters

import android.location.Location
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.config.topics.TopicsConfig
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
import demo.app.paintball.map.sensors.Locator
import demo.app.paintball.positioning.calculators.PositionCalculatorListener
import demo.app.paintball.positioning.calculators.gps.GpsPositionCalculator
import demo.app.paintball.positioning.calculators.gps.GpsPositionCalculatorImpl
import demo.app.paintball.positioning.calculators.uwb.UwbPositionCalculator
import demo.app.paintball.positioning.calculators.uwb.UwbPositionCalculatorImpl
import demo.app.paintball.positioning.converters.PositionConverter
import demo.app.paintball.ui.screens.MapScreen
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.toast
import javax.inject.Inject

class MapPresenter @Inject constructor() : Presenter<MapScreen>(), RestService.SuccessListener,
    MqttService.GameListener, MqttService.PositionListener, BleServiceImpl.BleServiceListener,
    PositionCalculatorListener, Locator.LocatorListener{

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    @Inject
    lateinit var bleService: BleService

    private lateinit var game: Game

    private lateinit var locator: Locator

    private lateinit var uwbPositionCalculator: UwbPositionCalculator
    private lateinit var gpsPositionCalculator: GpsPositionCalculator

    private lateinit var positionConverter: PositionConverter

    override fun attachScreen(screen: MapScreen) {
        super.attachScreen(screen)
        injector.inject(this)

        restService.apply { listener = this@MapPresenter; errorListener = ErrorHandler }
        mqttService.apply { positionListener = this@MapPresenter; gameListener = this@MapPresenter }
        bleService.also { it.addListener(this@MapPresenter) }

        bleService.startPositionSending()
        mqttService.subscribe(TopicsConfig.playerTopics.teamChat)
        mqttService.subscribe(TopicsConfig.playerTopics.positions)
    }

    fun queryGame(selectedGameId: Long) {
        restService.getGame(selectedGameId)
    }

    override fun onGetGame(game: Game) {
        this.game = game
        positionConverter = PositionConverter.create(game)
        initPositionCalculators()

        screen?.initMap(game)
        screen?.showUsers(game.bluePlayers, game.redPlayers)
        screen?.showAnchors(game.map!!.anchors, positionConverter)
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    override fun onGetUsers(users: List<User>) {
    }

    override fun onAddUserToTeam(team: Team) {
    }

    override fun positionMessageArrived(message: PositionMessage) {
        game.map?.let {
            screen?.setMovablePosition(
                message.playerName,
                positionConverter.mmToPx(message.posX),
                positionConverter.mmToPx(message.posY)
            )
        }
    }

    override fun connectComplete() {
        mqttService.subscribe(TopicsConfig.playerTopics.teamChat)
        mqttService.subscribe(TopicsConfig.playerTopics.positions)
    }

    override fun gameMessageArrived(message: GameMessage) {
        if (message.type == GameMessage.Type.LEAVE) {
            game.bluePlayers
                .union(game.redPlayers)
                .find { it.username == message.playerName }
                ?.let { leavingUser ->
                    game.deadPlayers.add(leavingUser)
                    screen?.removePlayer(message.playerName, game)
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
            screen?.setPlayerPosition(positionConverter.mmToPx(posX), positionConverter.mmToPx(posY))
        }
        PositionMessage(posX, posY).publish(mqttService)
    }

    private fun initPositionCalculators() {
        when (game.localizationMode) {
            Game.LocalizationMode.UWB -> {
                val anchorPositions = game.map!!.anchors.map { intArrayOf(it.x.toInt(), it.y.toInt(), 1000) }
                uwbPositionCalculator = UwbPositionCalculatorImpl(anchorPositions).apply { listener = this@MapPresenter }
            }
            Game.LocalizationMode.GPS -> {
                locator = Locator(listener = this)
                val originLocation = Location("").apply {
                    latitude = game.map!!.topLeftLatitude
                    longitude = game.map!!.topLeftLongitude
                }
                gpsPositionCalculator = GpsPositionCalculatorImpl(originLocation).apply { listener = this@MapPresenter }
            }
        }
    }

    override fun detachScreen() {
        bleService.removeListener(this)
        bleService.disconnectDevice()
        locator.cancel()
        super.detachScreen()
    }
}