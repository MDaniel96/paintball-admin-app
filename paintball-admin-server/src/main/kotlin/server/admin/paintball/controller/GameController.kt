package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.service.GameService

@RestController
@RequestMapping(GameController.BASE_URL)
class GameController(private val gameService: GameService) {

    companion object {
        const val BASE_URL = "/api/game"
    }

    @GetMapping
    fun getGames(): ResponseEntity<List<GameDTO>> {
        return ResponseEntity.ok(gameService.getGames())
    }
}
