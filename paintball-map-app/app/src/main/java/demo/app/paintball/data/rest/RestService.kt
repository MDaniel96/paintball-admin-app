package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
import retrofit2.Response

interface RestService {

    var listener: SuccessListener

    var errorListener: ErrorListener

    fun getCreatedGames()

    fun getGame()

    fun deleteGame()

    fun addRedPlayer(player: Player)

    fun addBluePlayer(player: Player)

    interface SuccessListener {
        fun getGameSuccess(response: Response<OldGame>)
        fun onGetCreatedGames(games: List<Game>)
        fun addRedPlayerSuccess()
        fun addBluePlayerSuccess()
    }

    interface ErrorListener {
        fun handleError(t: Throwable)
    }
}