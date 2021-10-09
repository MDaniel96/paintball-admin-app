package server.admin.paintball.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.request.CreateGameRequest
import server.admin.paintball.dto.util.GameFilter
import server.admin.paintball.model.Game

interface GameService {

    fun getGames(gameFilter: GameFilter): List<GameDTO>

    fun getGame(id: Long): GameDTO

    fun deleteGame(id: Long)

    fun editGame(id: Long, game: GameDTO): GameDTO

    fun getGamesPage(pageable: Pageable): Page<GameDTO>

    fun addUserToTeam(gameId: Long, userId: Long, team: Game.Team): GameDTO

    fun createGame(createGameRequest: CreateGameRequest): GameDTO

    fun changeGameState(id: Long, newState: Game.State): GameDTO

    fun kickPlayerFromGame(gameId: Long, playerId: Long): GameDTO

    fun sendVoiceMessageToTeam(gameId: Long, target: String, message: String)
}
