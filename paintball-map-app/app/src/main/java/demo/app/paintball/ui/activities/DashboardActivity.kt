package demo.app.paintball.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.R
import demo.app.paintball.ui.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.ui.fragments.dialogs.JoinGameFragment
import demo.app.paintball.ui.presenters.DashboardPresenter
import demo.app.paintball.ui.screens.DashboardScreen
import demo.app.paintball.util.fadeIn
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(), DashboardScreen, ConnectTagFragment.ConnectTagListener {

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter

    private val joinGameFragment = JoinGameFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        injector.inject(this)
        dashboardPresenter.attachScreen(this)

        btnJoinGame.setOnClickListener { joinGameFragment.show(supportFragmentManager, "TAG") }
        btnConnectTag.setOnClickListener { ConnectTagFragment().show(supportFragmentManager, "TAG") }
        checkTagsEnabled()
        initAnimations()
    }

    private fun checkTagsEnabled() {
        if (resources.getBoolean(R.bool.tagsEnabled)) {
            btnJoinGame.isEnabled = false
        } else {
            btnConnectTag.isEnabled = false
        }
    }

    override fun showCreatedGames() {
        joinGameFragment.showCreatedGames()
    }

    override fun onTagConnected() {
        btnJoinGame.isEnabled = true
        btnConnectTag.isEnabled = false
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

    override fun onDestroy() {
        dashboardPresenter.detachScreen()
        super.onDestroy()
    }
}