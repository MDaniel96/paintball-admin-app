package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User
import retrofit2.Response

interface RestService {

    var listener: SuccessListener

    var errorListener: ErrorListener

    fun getCreatedGames()

    fun getUsers()

    fun getGame(gameId: Long)

    fun deleteGame()

    fun addRedPlayer(player: Player)

    fun addBluePlayer(player: Player)

    interface SuccessListener {
        fun onGetGame(game: Game)
        fun onGetCreatedGames(games: List<Game>)
        fun onGetUsers(users: List<User>)
        fun addRedPlayerSuccess()
        fun addBluePlayerSuccess()
    }

    interface ErrorListener {
        fun handleError(t: Throwable)
    }
}