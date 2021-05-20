package server.admin.paintball.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.request.CreateGameRequest
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
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteGame(@PathVariable id: Long): ResponseEntity<Any> {
        gameService.deleteGame(id)
        return noContent().build()
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun editGame(@PathVariable id: Long, @RequestBody game: GameDTO): ResponseEntity<GameDTO> {
        return ok(gameService.editGame(id, game))
    }

    @GetMapping("/page")
    fun getGamesPage(
        @PageableDefault(page = 0, size = 10)
        @SortDefault(sort = ["name"], direction = Sort.Direction.ASC)
        pageable: Pageable, sort: Sort
    ): Page<GameDTO> {
        return gameService.getGamesPage(pageable)
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createGame(@RequestBody createGameRequest: CreateGameRequest): ResponseEntity<GameDTO> {
        return ok(gameService.createGame(createGameRequest))
    }

    @PatchMapping("/{id}/changeState")
    @PreAuthorize("hasRole('ADMIN')")
    fun changeState(
        @PathVariable id: Long, @RequestParam(required = true) newState: Game.State
    ): ResponseEntity<GameDTO> {
        return ok(gameService.changeGameState(id, newState))
    }

    @PatchMapping("/{id}/kickPlayer")
    @PreAuthorize("hasRole('ADMIN')")
    fun kickPlayerFromGame(
        @PathVariable id: Long, @RequestParam(required = true) playerId: Long
    ): ResponseEntity<GameDTO> {
        return ok(gameService.kickPlayerFromGame(id, playerId))
    }

    @PostMapping("/{id}/sendVoiceMessage")
    @PreAuthorize("hasRole('ADMIN')")
    fun sendVoiceMessageToTeam(
        @PathVariable id: Long,
        @RequestParam(required = true) target: String,
        @RequestBody message: String
    ): ResponseEntity<Unit> {
        gameService.sendVoiceMessageToTeam(id, target, message)
        return ok(Unit)
    }
}
