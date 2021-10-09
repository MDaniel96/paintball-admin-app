package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestServiceImpl @Inject constructor() : RestService {

    private val gameApi: GameApi = GameApi.create()

    override lateinit var listener: RestService.SuccessListener

    override lateinit var errorListener: RestService.ErrorListener

    override fun getCreatedGames() {
        gameApi.getCreatedGames().enqueue(object : Callback<List<Game>> {
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                listener.onGetCreatedGames(response.body())
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun getUsers() {
        gameApi.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                listener.onGetUsers(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun getGame(gameId: Long) {
        gameApi.getGame(gameId).enqueue(object : Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                listener.onGetGame(response.body())
            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun addUserToTeam(gameId: Long, userId: Long, team: Game.Team) {
        gameApi.addUserToTeam(gameId, userId, team).enqueue(object : Callback<Game.Team> {
            override fun onResponse(call: Call<Game.Team>, response: Response<Game.Team>) {
                listener.onAddUserToTeam(response.body())
            }

            override fun onFailure(call: Call<Game.Team>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }
}