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
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.setBackgroundTint
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.activity_join_game.*
import retrofit2.Response
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

    private var oldGame: OldGame? = null

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        restService = services.rest().apply { listener = this@JoinGameActivity; errorListener = ErrorHandler }
        mqttService = services.mqtt().apply { gameListener = this@JoinGameActivity }
        timer.schedule(timerTask {
            runOnUiThread { restService.getGame() }
        }, 0, GAME_REFRESH_PERIOD)
    }

    override fun getGameSuccess(response: Response<OldGame>) {
        if (response.code() == 404) {
            toast("No game found")
        } else {
            oldGame = response.body()
            initTexts()
            initStartGameButton()
            initTeamButtons()
            initTeamLists()
        }
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    private fun initTexts() {
        oldGame?.let {
            tvGameName.text = it.name
            tvGameType.text = String.format(getString(R.string.type_is), it.type)
            tvGameAdmin.text = String.format(getString(R.string.admin_is), it.admin)
            tvGamePlayerCnt.text = String.format(getString(R.string.player_cnt), it.playerCnt)
            btnViewRed.text = String.format(getString(R.string.view_players_), it.redTeam.size)
            btnViewBlue.text = String.format(getString(R.string.view_players_), it.blueTeam.size)
        }
    }

    private fun initStartGameButton() {
        if (!player.isAdmin) {
            btnStartGame.isEnabled = false
            btnStartGame.text = getString(R.string.waiting_for_admin)
        } else {
            btnStartGame.setOnClickListener {
                GameMessage(type = GameMessage.Type.START).publish(mqttService)
            }
        }
    }

    private fun initTeamButtons() {
        btnJoinRed.setOnClickListener {
            restService.addRedPlayer(player)
        }
        btnJoinBlue.setOnClickListener {
            restService.addBluePlayer(player)
        }
        btnViewRed.setOnClickListener {
            redExpansionLayout.toggle(true)
        }
        btnViewBlue.setOnClickListener {
            blueExpansionLayout.toggle(true)
        }
    }

    private fun initTeamLists() {
        oldGame?.let { game ->
            game.blueTeam.map { it.name }.run {
                lsBluePlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, this)
            }
            game.redTeam.map { it.name }.run {
                lsRedPlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, this)
            }
        }
    }

    override fun addRedPlayerSuccess() {
        restService.getGame()
        player.team = Player.Team.RED

        btnJoinRed.setBackgroundTint(R.color.redTeam)
        btnJoinRed.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnJoinBlue.setBackgroundTint(R.color.transparent)
        btnJoinBlue.setTextColor(ContextCompat.getColor(this, R.color.blueTeam))
        btnJoinRed.isEnabled = false
        btnJoinBlue.isEnabled = true
    }

    override fun addBluePlayerSuccess() {
        restService.getGame()
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

    override fun onBackPressed() {
        when {
            oldGame == null -> super.onBackPressed()
            player.isAdmin -> showDeleteGameAlert()
        }
    }

    private fun showDeleteGameAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit game")
        builder.setMessage("If you exit, the game will be deleted. Are you sure?")
        builder.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed()
            restService.deleteGame()
        }
        builder.setNeutralButton("Cancel") { _, _ ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}