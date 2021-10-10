package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.request.CreateGameRequest
import server.admin.paintball.dto.toDTO
import server.admin.paintball.dto.util.GameFilter
import server.admin.paintball.model.Game
import server.admin.paintball.repository.GameRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException

@Service
class GameServiceImpl(
    private val gameRepository: GameRepository,
    private val mapService: MapService,
    private val userService: UserService,
    private val mqttService: MqttService,
    private val mapper: ModelMapper
) : GameService {

    override fun getGames(gameFilter: GameFilter): List<GameDTO> {
        val gameList = gameFilter.run {
            gameRepository.findAllByStateAndNameContainsAndTypeInAndDate(state, name, types, date)
        }
        return gameList.toDTO(mapper)
    }

    override fun getGame(id: Long): GameDTO {
        return getGameById(id).toDTO(mapper)
    }

    @Transactional
    override fun deleteGame(id: Long) {
        val game = getGameById(id)
        gameRepository.delete(game)
    }

    @Transactional
    override fun editGame(id: Long, game: GameDTO): GameDTO {
        return getGameById(id).apply {
            name = game.name
            type = game.type
            state = game.state
            date = game.date
            localizationMode = game.localizationMode
        }.run {
            toDTO(mapper)
        }
    }

    @Transactional
    override fun createGame(createGameRequest: CreateGameRequest): GameDTO {
        return createGameRequest.run {
            val map = mapService.getMapById(createGameRequest.mapId)
            val game = gameRepository.save(
                Game(
                    name = name,
                    type = type,
                    map = map,
                    localizationMode = localizationMode
                )
            )
            game.toDTO(mapper)
        }
    }

    @Transactional
    override fun changeGameState(id: Long, newState: Game.State): GameDTO {
        if (newState == Game.State.STARTED) {
            val adminName = userService.getCurrentUser().username
            mqttService.publish("game", "$adminName|START")
        }

        return getGameById(id).apply {
            state = newState
        }.run {
            toDTO(mapper)
        }
    }

    @Transactional
    override fun kickPlayerFromGame(gameId: Long, playerId: Long): GameDTO {
        return getGameById(gameId).apply {
            val player = userService.getUserById(playerId)
            deadPlayers.add(player)
        }.run {
            toDTO(mapper)
        }
    }

    override fun sendVoiceMessageToTeam(gameId: Long, target: String, message: String) {
        println(
            ("""
            gameId: $gameId,
            target: $target,
            message: ${message.length}
        """.trimIndent())
        )
        getGameById(gameId).run {
            // TODO("Not yet implemented")
            when (target) {
                "red" -> {
                    // sendToRedTeam()
                }
                "blue" -> {
                    // sendToBlueTeam()
                }
                "both" -> {
                    // sendToBothTeams()
                }
                else -> {
                    // error, nothing to do
                }
            }
        }
    }

    override fun getGamesPage(pageable: Pageable): Page<GameDTO> {
        val gameToDTO: (Game) -> GameDTO = { it.toDTO(mapper) }
        return gameRepository.findAll(pageable)
            .map(gameToDTO)
    }

    @Transactional
    override fun addUserToTeam(gameId: Long, userId: Long, team: Game.Team): Game.Team {
        val user = userService.getUserById(userId)
        getGameById(gameId).apply {
            when (team) {
                Game.Team.RED -> redPlayers.add(user)
                Game.Team.BLUE -> bluePlayers.add(user)
            }
        }
        return team
    }

    private fun getGameById(id: Long): Game {
        return gameRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Game not found")
    }
}
