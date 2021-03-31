package server.admin.paintball.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.util.GameFilter

interface GameService {

    fun getGames(gameFilter: GameFilter?): List<GameDTO>

    fun getGame(id: Long): GameDTO

    fun deleteGame(id: Long)

    fun editGame(id: Long, game: GameDTO): GameDTO

    fun getGamesPage(pageable: Pageable): Page<GameDTO>
}
