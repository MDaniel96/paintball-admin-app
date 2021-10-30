package server.admin.paintball.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.modelmapper.ModelMapper
import server.admin.paintball.dto.UserDTO
import server.admin.paintball.model.Game
import server.admin.paintball.model.User
import server.admin.paintball.repository.GameRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException
import java.util.*

@ExtendWith(MockitoExtension::class)
class GameServiceTest {

    @InjectMocks
    lateinit var gameService: GameServiceImpl

    @Spy
    lateinit var gameRepository: GameRepository

    @Mock
    lateinit var mapService: MapService

    @Mock
    lateinit var userService: UserService

    @Mock
    lateinit var mqttService: MqttService

    @Spy
    lateinit var mapper: ModelMapper

    @Nested
    inner class ChangeGameState {

        @Test
        fun `should publish start game when new state is STARTED`() {
            val startedState = Game.State.STARTED
            val admin = UserDTO(username = "Admin")
            doReturn(admin).`when`(userService).getCurrentUser()
            doReturn(Optional.of(Game())).`when`(gameRepository).findById(anyLong())

            gameService.changeGameState(1L, startedState)

            verify(mqttService).publish("game", "${admin.username}|START")
        }

        @Test
        fun `should throw EntityNotFoundException when provided id is invalid`() {
            doReturn(Optional.empty<Game>()).`when`(gameRepository).findById(anyLong())

            assertThrows(EntityNotFoundException::class.java) {
                gameService.changeGameState(1L, Game.State.CREATED)
            }
        }

        @Test
        fun `should set game state to FINISHED`() {
            val finishedState = Game.State.FINISHED
            doReturn(Optional.of(Game())).`when`(gameRepository).findById(anyLong())

            val changedGame = gameService.changeGameState(1L, finishedState)

            assertEquals(Game.State.FINISHED, changedGame.state)
        }
    }

    @Nested
    inner class AddUserToTeam {
        private lateinit var user: User
        private lateinit var game: Game

        @BeforeEach
        fun setup() {
            user = User()
            game = Game()
            doReturn(user).`when`(userService).getUserById(anyLong())
            doReturn(Optional.of(game)).`when`(gameRepository).findById(anyLong())
        }

        @Test
        fun `should add user to red players`() {
            gameService.addUserToTeam(1L, 1L, Game.Team.RED)

            assertTrue(game.redPlayers.contains(user))
        }

        @Test
        fun `should add user to blue players`() {
            gameService.addUserToTeam(1L, 1L, Game.Team.BLUE)

            assertTrue(game.bluePlayers.contains(user))
        }
    }
}