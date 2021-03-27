package server.admin.paintball.service

import server.admin.paintball.dto.GameDTO

interface GameService {

    fun getGames(): List<GameDTO>
}
