package demo.app.paintball.data.rest

import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.OldGame
import demo.app.paintball.data.rest.models.Player
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApi {

    @GET("/paintball-admin/api/game?state=CREATED")
    fun getCreatedGames(): Call<List<Game>>

    @GET("/api/game")
    fun getGame(): Call<OldGame>

    @DELETE("/api/game")
    fun deleteGame(): Call<Any>

    @POST("/api/game/red")
    fun addRedPlayer(@Body player: Player): Call<Any>

    @POST("/api/game/blue")
    fun addBluePlayer(@Body player: Player): Call<Any>

    companion object {
        private val BASE_URL = "http://${context.getString(R.string.serverURL)}:8080/"

        fun create(): GameApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GameApi::class.java)
        }
    }
}