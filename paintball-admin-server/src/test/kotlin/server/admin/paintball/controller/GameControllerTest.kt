package server.admin.paintball.controller

import io.restassured.RestAssured
import io.restassured.RestAssured.`when`
import io.restassured.RestAssured.given
import io.restassured.authentication.FormAuthConfig
import io.restassured.authentication.FormAuthScheme
import io.restassured.mapper.TypeRef
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.model.Game
import server.admin.paintball.repository.GameRepository
import server.admin.paintball.security.SecurityConfig
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @LocalServerPort
    private var port = 0

    @Autowired
    lateinit var gameRepository: GameRepository

    @BeforeEach
    fun setup() {
        RestAssured.port = port
        RestAssured.basePath = "/paintball-admin" + "/api/game"
        RestAssured.authentication = getAuthScheme()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        gameRepository.deleteAll()
    }

    private fun getAuthScheme(): FormAuthScheme {
        return FormAuthScheme().apply {
            userName = "admin"
            password = "admin"
            config = FormAuthConfig("paintball-admin" + SecurityConfig.LOGIN_URL, "username", "password")
        }
    }

    @Nested
    inner class GetGames {

        @Test
        fun `should return list of all games`() {
            gameRepository.saveAll(
                listOf(
                    Game(name = "Game1"),
                    Game(name = "Game2")
                )
            )

            val gameList: List<GameDTO> = `when`()
                .get("")
                .then().statusCode(`is`(200))
                .extract().`as`(object : TypeRef<List<GameDTO>>() {})

            assertEquals(2, gameList.size)
            assertEquals("Game1", gameList[0].name)
            assertEquals("Game2", gameList[1].name)
        }

        @Test
        fun `should only return games which match filter conditions`() {
            gameRepository.saveAll(
                listOf(
                    Game(name = "Game1", type = "DM"),
                    Game(name = "Game2"),
                    Game(name = "Game3", date = LocalDate.now().minusDays(1)),
                    Game(name = "Game4", state = Game.State.STARTED),
                    Game(name = "NoGam5")
                )
            )

            val gameList: List<GameDTO> = given()
                .queryParams(mapOf(
                    "name" to "Game",
                    "state" to "CREATED",
                    "date" to LocalDate.now().toString(),
                    "type" to "DM"
                ))
                .`when`().get("")
                .then().statusCode(`is`(200))
                .extract().`as`(object : TypeRef<List<GameDTO>>() {})

            assertEquals(1, gameList.size)
            assertEquals("Game1", gameList[0].name)
        }
    }

    @Nested
    inner class DeleteGame {

        @Test
        fun `should return 401 if game to delete is not found`() {
            gameRepository.save(Game(id = 1L))

            given()
                .pathParams(mapOf(
                    "id" to "2"
                ))
                .`when`().delete("/{id}")
                .then().statusCode(`is`(404))
        }
    }
}