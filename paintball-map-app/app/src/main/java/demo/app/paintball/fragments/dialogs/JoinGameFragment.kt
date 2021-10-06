package demo.app.paintball.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.fragment_join_game.*
import retrofit2.Response
import javax.inject.Inject

class JoinGameFragment : DialogFragment(), RestService.SuccessListener {

    private lateinit var listener: JoinGameListener

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

        btnDone.setOnClickListener {
            val name = etPlayerName.text.toString()
            val errorMsg = getString(R.string.fill_out)

            if (name == "") {
                etPlayerName.error = errorMsg
                return@setOnClickListener
            }

            // TODO: return to activity if game selected
            // listener.onJoinGame(etPlayerName.text.toString())
            // dismiss()
        }
    }

    override fun getGameSuccess(response: Response<OldGame>) {
    }

    override fun onGetCreatedGames(games: List<Game>) {
        // TODO: display created games
    }

    override fun addRedPlayerSuccess() {
    }

    override fun addBluePlayerSuccess() {
    }

    interface JoinGameListener {
        fun onJoinGame(playerName: String)
    }
}