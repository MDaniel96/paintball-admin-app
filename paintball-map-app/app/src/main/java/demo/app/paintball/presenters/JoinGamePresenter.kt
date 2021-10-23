package demo.app.paintball.presenters

import android.app.Activity
import android.content.Intent
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.activities.MapActivity
import demo.app.paintball.config.topics.TopicsConfig
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.screens.JoinGameScreen
import demo.app.paintball.util.ErrorHandler
import java.util.*
import javax.inject.Inject

class JoinGamePresenter @Inject constructor() : Presenter<JoinGameScreen>(),
    RestService.SuccessListener, MqttService.GameListener {

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    private lateinit var game: Game

    private var joinedTeam: Team? = null

    override fun attachScreen(screen: JoinGameScreen) {
        super.attachScreen(screen)
        injector.inject(this)

        restService.apply { listener = this@JoinGamePresenter; errorListener = ErrorHandler }
        mqttService.apply { gameListener = this@JoinGamePresenter }
    }

    fun queryGame(selectedGameId: Long) {
        restService.getGame(selectedGameId)
    }

    fun joinTeam(team: Team) {
        if (joinedTeam == null) {
            restService.addUserToTeam(game.id, PaintballApplication.currentUser.id, team)
            PaintballApplication.currentTeam = Team.BLUE
        }
    }

    override fun onGetGame(game: Game) {
        this.game = game
        screen?.showGameDetails(game)
        screen?.showTeamLists(game.bluePlayers, game.redPlayers)
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    override fun onGetUsers(users: List<User>) {
    }

    override fun onAddUserToTeam(team: Team) {
        joinedTeam = team
        when (team) {
            Team.RED -> screen?.displayAddRedPlayer()
            Team.BLUE -> screen?.displayAddBluePlayer()
        }
    }

    override fun connectComplete() {
        mqttService.subscribe(TopicsConfig.playerTopics.game)
    }

    override fun gameMessageArrived(message: GameMessage) {
        if (message.type == GameMessage.Type.START && joinedTeam != null) {
            screen?.stopTimer()
            val intent = Intent(screen as Activity, MapActivity::class.java)
            intent.putExtra("SELECTED_GAME_ID", game.id)
            (screen as Activity).startActivity(intent)
        }
    }
}