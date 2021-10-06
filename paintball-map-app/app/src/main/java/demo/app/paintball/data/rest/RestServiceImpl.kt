package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
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

    override fun getGame() {
        gameApi.getGame().enqueue(object : Callback<OldGame> {
            override fun onResponse(call: Call<OldGame>, response: Response<OldGame>) {
                listener.getGameSuccess(response)
            }

            override fun onFailure(call: Call<OldGame>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun deleteGame() {
        gameApi.deleteGame().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                toast("Game deleted")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun addRedPlayer(player: Player) {
        gameApi.addRedPlayer(player).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                listener.addRedPlayerSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun addBluePlayer(player: Player) {
        gameApi.addBluePlayer(player).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                listener.addBluePlayerSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }
}