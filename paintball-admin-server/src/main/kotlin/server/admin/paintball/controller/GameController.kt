package server.admin.paintball.controller

import org.springframework.format.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.util.GameFilter
import server.admin.paintball.model.Game
import server.admin.paintball.service.GameService
import java.time.LocalDate

@RestController
@RequestMapping(GameController.BASE_URL)
class GameController(private val gameService: GameService) {

    companion object {
        const val BASE_URL = "/api/game"
    }

    @GetMapping
    fun getGames(
        @RequestParam(required = false) state: Game.State?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) type: List<String>?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?
    ): ResponseEntity<List<GameDTO>> {
        val gameFilter = GameFilter(state, name, type, date)
        return ResponseEntity.ok(gameService.getGames(gameFilter))
    }
}
