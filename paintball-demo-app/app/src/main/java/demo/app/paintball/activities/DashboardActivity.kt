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
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.dialogs.CreateGameFragment
import demo.app.paintball.fragments.dialogs.JoinGameFragment
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.checkPermissions
import demo.app.paintball.util.fadeIn
import kotlinx.android.synthetic.main.activity_dashboard.*
import retrofit2.Response
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(), RestService.SuccessListener,
    JoinGameFragment.JoinGameListener, CreateGameFragment.CreateGameListener, ConnectTagFragment.ConnectTagListener {

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

    private lateinit var playerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        restService = services.rest().apply { listener = this@DashboardActivity; errorListener = ErrorHandler }

        btnCreateGame.setOnClickListener { CreateGameFragment().show(supportFragmentManager, "TAG") }
        btnJoinGame.setOnClickListener { JoinGameFragment().show(supportFragmentManager, "TAG") }
        btnConnectTag.setOnClickListener { ConnectTagFragment().show(supportFragmentManager, "TAG") }
        checkTagsEnabled()
        this.checkPermissions(permissionsNeeded)
        checkOnlyMapMode()
        initAnimations()
    }

    private fun checkTagsEnabled() {
        if (resources.getBoolean(R.bool.tagsEnabled)) {
            btnCreateGame.isEnabled = false
            btnJoinGame.isEnabled = false
        } else {
            btnConnectTag.isEnabled = false
        }
    }

    override fun onJoinGame(playerName: String) {
        player = Player(name = playerName, isAdmin = false)
        val intent = Intent(this, JoinGameActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateGame(playerName: String, game: Game) {
        this.playerName = playerName
        restService.createGame(game)
    }

    override fun getGameSuccess(response: Response<Game>) {
    }

    override fun createGameSuccess() {
        player = Player(name = playerName, isAdmin = true)
        val intent = Intent(this, JoinGameActivity::class.java)
        startActivity(intent)
    }

    override fun addRedPlayerSuccess() {
    }

    override fun addBluePlayerSuccess() {
    }

    override fun onTagConnected() {
        btnCreateGame.isEnabled = true
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
        btnCreateGame.fadeIn(800)
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