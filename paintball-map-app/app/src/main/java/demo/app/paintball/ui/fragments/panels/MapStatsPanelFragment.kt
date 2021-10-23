package demo.app.paintball.ui.fragments.panels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import demo.app.paintball.R
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import kotlinx.android.synthetic.main.fragment_map_stats.*

class MapStatsPanelFragment : Fragment() {

    private lateinit var statsLayout: View
    private lateinit var statsDetailLayout: View
    private lateinit var mainStatsLayout: View

    private var pDownX = 0
    private var detailsOpen = false
    private var dragged = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainStatsLayout = view.findViewById<LinearLayout>(R.id.mainStatsLayout)
        statsDetailLayout = view.findViewById<LinearLayout>(R.id.statsDetailLayout)
        statsLayout = view.findViewById<LinearLayout>(R.id.statsLayout).apply {
            translationX = -resources.getDimension(R.dimen.stat_details_layout_width)
        }

        statsLayout.setOnTouchListener { v, event ->
            v.performClick()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dragged = false
                    pDownX = event.x.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    dragged = true
                    statsLayout.translationX += event.x.toInt() - pDownX
                }
                MotionEvent.ACTION_UP -> {
                    if (dragged) {
                        if (statsDetailLayout.width + statsLayout.translationX < statsDetailLayout.width * 1 / 2) {
                            hideDetails()
                        } else {
                            showDetails()
                        }
                    } else {
                        if (detailsOpen) showDetails() else hideDetails()
                        detailsOpen = !detailsOpen
                    }
                }
            }
            true
        }
    }

    fun refresh(game: Game) {
        tvBlueTeam.text = getString(R.string.remaining_players, game.getRemainingPlayers(Team.BLUE), game.bluePlayers.size)
        tvRedTeam.text = getString(R.string.remaining_players, game.getRemainingPlayers(Team.RED), game.redPlayers.size)
    }

    fun show() {
        statsLayout.animate().translationX(-statsDetailLayout.width.toFloat())
    }

    fun hide() {
        hideDetails()
        val translateX = -resources.getDimension(R.dimen.stats_layout_width) -
                resources.getDimension(R.dimen.stat_details_layout_width) -
                resources.getDimension(R.dimen.stat_drag_button_width)
        statsLayout.animate().translationX(translateX)
    }

    private fun showDetails() {
        statsLayout.animate().translationX(0F)
        btnDrag.animate().rotation(-180F)
        detailsOpen = true
    }

    private fun hideDetails() {
        statsLayout.animate().translationX(-statsDetailLayout.width.toFloat())
        btnDrag.animate().rotation(0F)
        detailsOpen = false
    }
}