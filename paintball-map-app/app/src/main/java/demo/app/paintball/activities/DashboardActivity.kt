package demo.app.paintball.activities

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.dialogs.JoinGameFragment
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.checkPermissions
import demo.app.paintball.util.fadeIn
import kotlinx.android.synthetic.main.activity_dashboard.*
import retrofit2.Response
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(), RestService.SuccessListener,
    JoinGameFragment.JoinGameListener, ConnectTagFragment.ConnectTagListener {

    companion object {
        val permissionsNeeded = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    @Inject
    lateinit var restService: RestService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        restService = services.rest().apply { listener = this@DashboardActivity; errorListener = ErrorHandler }

        btnJoinGame.setOnClickListener { JoinGameFragment().show(supportFragmentManager, "TAG") }
        btnConnectTag.setOnClickListener { ConnectTagFragment().show(supportFragmentManager, "TAG") }
        checkTagsEnabled()
        this.checkPermissions(permissionsNeeded)
        checkOnlyMapMode()
        initAnimations()
    }

    private fun checkTagsEnabled() {
        if (resources.getBoolean(R.bool.tagsEnabled)) {
            btnJoinGame.isEnabled = false
        } else {
            btnConnectTag.isEnabled = false
        }
    }

    override fun onJoinGame(selectedGameId: Long) {
        val intent = Intent(this, JoinGameActivity::class.java)
        intent.putExtra("SELECTED_GAME_ID", selectedGameId)
        startActivity(intent)
    }

    override fun onGetGame(game: Game) {
    }

    override fun onGetCreatedGames(games: List<Game>) {
    }

    override fun onGetUsers(users: List<User>) {
    }

    override fun onAddUserToTeam(team: Game.Team) {
    }

    override fun onTagConnected() {
        btnJoinGame.isEnabled = true
        btnConnectTag.isEnabled = false
    }

    private fun checkOnlyMapMode() {
        if (resources.getBoolean(R.bool.mapOnlyMode)) {
            player = Player(name = "OnlyMapPlayer", isAdmin = true)
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAnimations() {
        btnJoinGame.fadeIn(800)
        btnConnectTag.fadeIn(800)
        imgIcon.animate()
            .scaleX(0.8F)
            .scaleY(0.8F)
            .translationY(-150F)
            .setDuration(800).startDelay = 300
        Glide.with(this).load(R.drawable.gif_icon).into(imgIcon)
    }
}