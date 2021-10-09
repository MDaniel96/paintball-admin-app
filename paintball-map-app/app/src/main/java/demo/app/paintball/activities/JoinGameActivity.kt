package demo.app.paintball.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.setBackgroundTint
import kotlinx.android.synthetic.main.activity_join_game.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class JoinGameActivity : AppCompatActivity(), RestService.SuccessListener, MqttService.GameListener {

    companion object {
        const val GAME_REFRESH_PERIOD = 3_000L
    }

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    private lateinit var game: Game

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)
        initStartGameButton()
        val selectedGameId = intent.getLongExtra("SELECTED_GAME_ID", -1L)

        restService = services.rest().apply { listener = this@JoinGameActivity; errorListener = ErrorHandler }
        mqttService = services.mqtt().apply { gameListener = this@JoinGameActivity }
        timer.schedule(timerTask {
            runOnUiThread { restService.getGame(selectedGameId) }
        }, 0, GAME_REFRESH_PERIOD)
    }

    override fun onGetGame(game: Game) {
        this.game = game
        initTexts()
        initTeamButtons()
        initTeamLists()
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    override fun onGetUsers(users: List<User>) {
    }

    private fun initTexts() {
        game.run {
            tvGameName.text = name
            tvGameType.text = String.format(getString(R.string.type_is), type)
            tvLocalizationMode.text = String.format(getString(R.string.localization_mode_is), localizationMode)
            tvGamePlayerCnt.text = String.format(getString(R.string.player_cnt), playerCount)
            tvMapName.text = String.format(getString(R.string.map_name_is), map?.name)
            btnViewRed.text = String.format(getString(R.string.view_players_), redPlayers.size)
            btnViewBlue.text = String.format(getString(R.string.view_players_), bluePlayers.size)
        }
    }

    private fun initStartGameButton() {
        btnStartGame.isEnabled = false
        btnStartGame.text = getString(R.string.waiting_for_admin)
    }

    private fun initTeamButtons() {
        // TODO handle join game buttons
//        btnJoinRed.setOnClickListener {
//            restService.addRedPlayer(player)
//        }
//        btnJoinBlue.setOnClickListener {
//            restService.addBluePlayer(player)
//        }
        btnViewRed.setOnClickListener {
            redExpansionLayout.toggle(true)
        }
        btnViewBlue.setOnClickListener {
            blueExpansionLayout.toggle(true)
        }
    }

    private fun initTeamLists() {
        game.run {
            bluePlayers.map { it.username }.also { playerNames ->
                lsBluePlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, playerNames)
            }
            redPlayers.map { it.username }.also { playerNames ->
                lsRedPlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, playerNames)
            }
        }
    }

    override fun addRedPlayerSuccess() {
        // TODO add player callback
        // restService.getGame()
        player.team = Player.Team.RED

        btnJoinRed.setBackgroundTint(R.color.redTeam)
        btnJoinRed.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnJoinBlue.setBackgroundTint(R.color.transparent)
        btnJoinBlue.setTextColor(ContextCompat.getColor(this, R.color.blueTeam))
        btnJoinRed.isEnabled = false
        btnJoinBlue.isEnabled = true
    }

    override fun addBluePlayerSuccess() {
        // TODO add player callback
        // restService.getGame()
        player.team = Player.Team.BLUE

        btnJoinBlue.setBackgroundTint(R.color.blueTeam)
        btnJoinBlue.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnJoinRed.setBackgroundTint(R.color.transparent)
        btnJoinRed.setTextColor(ContextCompat.getColor(this, R.color.redTeam))
        btnJoinBlue.isEnabled = false
        btnJoinRed.isEnabled = true
    }

    override fun connectComplete() {
        mqttService.subscribe(playerTopics.game)
    }

    override fun gameMessageArrived(message: GameMessage) {
        if (message.type == GameMessage.Type.START && player.team != null) {
            timer.cancel()
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}