package demo.app.paintball.ui.fragments.buttons

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.R
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.ui.presenters.MapPresenter
import demo.app.paintball.util.ButtonProgressDisplayer
import kotlinx.android.synthetic.main.fragment_main_buttons.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MainButtonsFragmentImpl : MapButtonsFragment() {

    companion object {
        const val SPYING_TIME = 15_000L
        const val SPYING_RECHARGE_TIME = 10_000L
    }

    override lateinit var rootLayout: View

    override lateinit var btnBottom: View
    override lateinit var btnBottomLayout: View
    override lateinit var btnBottomTextView: View

    override lateinit var btnMiddle: View
    override lateinit var btnMiddleLayout: View
    override lateinit var btnMiddleTextView: View

    @Inject
    lateinit var mapPresenter: MapPresenter

    private val timer = Timer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injector.inject(this)

        return inflater.inflate(R.layout.fragment_main_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootLayout = mainButtonsLayout
        btnBottom = fabLeaveGame
        btnBottomLayout = fabLayoutLeaveGame
        btnBottomTextView = fabTextViewLeaveGame
        btnMiddle = fabSpying
        btnMiddleLayout = fabLayoutSpying
        btnMiddleTextView = fabTextViewSpying
        initFabSpyingButton()
    }

    private fun initFabSpyingButton() {
        val rootActivity = activity as Activity
        val fabProgressDisplayer = ButtonProgressDisplayer(fabSpying, rootActivity)
        fabSpying.setOnClickListener {
            mapPresenter.mqttService.subscribe(playerTopics.enemyPositions)
            fabSpying.isEnabled = false
            fabSpying.setColor(ContextCompat.getColor(PaintballApplication.context, R.color.lightTransparentGray))

            timer.schedule(SPYING_TIME) {
                rootActivity.runOnUiThread {
                    mapPresenter.mqttService.unsubscribe(playerTopics.enemyPositions)
                    fabProgressDisplayer.show(SPYING_RECHARGE_TIME)
                }
            }
            timer.schedule(SPYING_TIME + SPYING_RECHARGE_TIME) {
                rootActivity.runOnUiThread {
                    fabSpying.isEnabled = true
                    fabSpying.setColor(ContextCompat.getColor(PaintballApplication.context, R.color.primaryLightColor))
                }
            }
        }
    }
}