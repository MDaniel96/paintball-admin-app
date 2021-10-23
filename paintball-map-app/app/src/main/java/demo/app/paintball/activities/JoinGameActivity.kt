package demo.app.paintball.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.R
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.presenters.JoinGamePresenter
import demo.app.paintball.screens.JoinGameScreen
import demo.app.paintball.util.setBackgroundTint
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.activity_join_game.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class JoinGameActivity : AppCompatActivity(), JoinGameScreen {

    companion object {
        const val GAME_REFRESH_PERIOD = 3_000L
    }

    @Inject
    lateinit var joinGamePresenter: JoinGamePresenter

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)
        injector.inject(this)
        joinGamePresenter.attachScreen(this)

        initStartGameButton()
        initTeamButtons()

        val selectedGameId = intent.getLongExtra("SELECTED_GAME_ID", -1L)
        timer.schedule(timerTask {
            runOnUiThread { joinGamePresenter.queryGame(selectedGameId) }
        }, 0, GAME_REFRESH_PERIOD)
    }

    private fun initStartGameButton() {
        btnStartGame.isEnabled = false
        btnStartGame.text = getString(R.string.waiting_for_admin)
    }

    private fun initTeamButtons() {
        btnJoinRed.setOnClickListener { joinGamePresenter.joinTeam(Team.RED) }
        btnJoinBlue.setOnClickListener { joinGamePresenter.joinTeam(Team.BLUE) }
        btnViewRed.setOnClickListener { redExpansionLayout.toggle(true) }
        btnViewBlue.setOnClickListener { blueExpansionLayout.toggle(true) }
    }

    override fun showGameDetails(game: Game) {
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

    override fun showTeamLists(bluePlayers: MutableSet<User>, redPlayers: MutableSet<User>) {
        bluePlayers.map { it.username }.also { playerNames ->
            lsBluePlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, playerNames)
        }
        redPlayers.map { it.username }.also { playerNames ->
            lsRedPlayers.adapter = ArrayAdapter(context, R.layout.list_item_player, playerNames)
        }
    }

    override fun displayAddRedPlayer() {
        btnJoinRed.setBackgroundTint(R.color.redTeam)
        btnJoinRed.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnJoinBlue.setBackgroundTint(R.color.transparent)
        btnJoinBlue.setTextColor(ContextCompat.getColor(this, R.color.blueTeam))
        btnJoinRed.isEnabled = false
        btnJoinBlue.isEnabled = true
    }

    override fun displayAddBluePlayer() {
        btnJoinBlue.setBackgroundTint(R.color.blueTeam)
        btnJoinBlue.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnJoinRed.setBackgroundTint(R.color.transparent)
        btnJoinRed.setTextColor(ContextCompat.getColor(this, R.color.redTeam))
        btnJoinBlue.isEnabled = false
        btnJoinRed.isEnabled = true
    }

    override fun stopTimer() {
        timer.cancel()
    }

    override fun onBackPressed() {
        toast("Can't turn back")
    }

    override fun onDestroy() {
        timer.cancel()
        joinGamePresenter.detachScreen()
        super.onDestroy()
    }
}