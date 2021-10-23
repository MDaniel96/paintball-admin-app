package demo.app.paintball.ui.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.R
import demo.app.paintball.ui.presenters.DashboardPresenter
import kotlinx.android.synthetic.main.fragment_join_game.*
import javax.inject.Inject

class JoinGameFragment : DialogFragment() {

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_join_game, container, false)
        dialog?.setTitle(R.string.itemPlayerName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardPresenter.queryJoinGameData()

        btnDone.setOnClickListener {
            val selectedUser = dashboardPresenter.users.find { it.username == etPlayerName.text.toString() }
            val selectedGame = dashboardPresenter.createdGames.find { it.name == spGames.selectedItem.toString() }

            if (etPlayerName.text.toString() == "") {
                etPlayerName.error = getString(R.string.fill_out)
                return@setOnClickListener
            } else if (selectedUser == null) {
                etPlayerName.error = getString(R.string.username_not_found)
                return@setOnClickListener
            }
            if (selectedGame == null) {
                return@setOnClickListener
            }

            dashboardPresenter.joinGame(selectedGame.id, selectedUser)
            dismiss()
        }
    }

    fun showCreatedGames() {
        ArrayAdapter(
            PaintballApplication.context,
            android.R.layout.simple_spinner_item,
            dashboardPresenter.createdGames.map { it.name })
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spGames.adapter = adapter
            }
    }
}