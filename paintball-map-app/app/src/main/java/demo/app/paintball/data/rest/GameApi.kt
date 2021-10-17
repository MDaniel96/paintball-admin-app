package demo.app.paintball.data.rest

import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GameApi {

    @GET("/paintball-admin/api/game?state=CREATED")
    fun getCreatedGames(): Call<List<Game>>

    @GET("/paintball-admin/api/user")
    fun getUsers(): Call<List<User>>

    @GET("/paintball-admin/api/game/{gameId}")
    fun getGame(@Path("gameId") gameId: Long): Call<Game>

    @POST("/paintball-admin/api/game/{gameId}/{userId}/{team}")
    fun addUserToTeam(
        @Path("gameId") gameId: Long,
        @Path("userId") userId: Long,
        @Path("team") team: Team,
    ): Call<Team>

    companion object {
        val BASE_URL = "http://${context.getString(R.string.serverURL)}:8080/"

        fun create(): GameApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GameApi::class.java)
        }
    }
}