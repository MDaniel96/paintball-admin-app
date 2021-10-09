package demo.app.paintball.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.fragment_join_game.*
import retrofit2.Response
import javax.inject.Inject

class JoinGameFragment : DialogFragment(), RestService.SuccessListener {

    private lateinit var listener: JoinGameListener

    private var createdGames = emptyList<Game>()
    private var users = emptyList<User>()

    @Inject
    lateinit var restService: RestService

    override fun onAttach(context: Context) {
        super.onAttach(context)

        restService = services.rest().apply { listener = this@JoinGameFragment; errorListener = ErrorHandler }

        try {
            listener = activity as JoinGameListener
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_join_game, container, false)
        dialog?.setTitle(R.string.itemPlayerName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restService.getCreatedGames()
        restService.getUsers()

        btnDone.setOnClickListener {
            val selectedUser = users.find { it.username == etPlayerName.text.toString() }
            val selectedGame = createdGames.find { it.name == spGames.selectedItem.toString() }

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

            PaintballApplication.user = selectedUser
            listener.onJoinGame(selectedGame.id)
            dismiss()
        }
    }

    override fun onGetGame(game: Game) {
    }

    override fun onGetCreatedGames(games: List<Game>) {
        createdGames = games
        ArrayAdapter(PaintballApplication.context, android.R.layout.simple_spinner_item, games.map { it.name })
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spGames.adapter = adapter
            }
    }

    override fun onGetUsers(users: List<User>) {
        this.users = users
    }

    override fun onAddUserToTeam(team: Game.Team) {
    }

    interface JoinGameListener {
        fun onJoinGame(selectedGameId: Long)
    }
}