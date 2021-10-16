package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User

interface RestService {

    var listener: SuccessListener

    var errorListener: ErrorListener

    fun getCreatedGames()

    fun getUsers()

    fun getGame(gameId: Long)

    fun addUserToTeam(gameId: Long, userId: Long, team: Team)

    interface SuccessListener {
        fun onGetGame(game: Game)
        fun onGetCreatedGames(games: List<Game>)
        fun onGetUsers(users: List<User>)
        fun onAddUserToTeam(team: Team)
    }

    interface ErrorListener {
        fun handleError(t: Throwable)
    }
}