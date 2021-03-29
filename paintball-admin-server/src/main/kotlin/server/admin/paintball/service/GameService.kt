package server.admin.paintball.service

import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.util.GameFilter

interface GameService {

    fun getGames(gameFilter: GameFilter?): List<GameDTO>
}
