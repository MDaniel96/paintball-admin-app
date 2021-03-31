package server.admin.paintball.controller

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
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
        return ok(gameService.getGames(gameFilter))
    }

    @GetMapping("{id}")
    fun getGame(@PathVariable id: Long): ResponseEntity<GameDTO> {
        return ok(gameService.getGame(id))
    }

    @DeleteMapping("{id}")
    fun deleteGame(@PathVariable id: Long): ResponseEntity<Any> {
        gameService.deleteGame(id)
        return noContent().build()
    }

    @PutMapping("{id}")
    fun editGame(@PathVariable id: Long, @RequestBody game: GameDTO): ResponseEntity<GameDTO> {
        return ok(gameService.editGame(id, game))
    }
}
