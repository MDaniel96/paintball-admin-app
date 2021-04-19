package server.admin.paintball.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.ObstacleDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.service.MapService

@RestController
@RequestMapping(MapController.BASE_URL)
class MapController(private val mapService: MapService) {

    companion object {
        const val BASE_URL = "/api/map"
    }

    @GetMapping
    fun getMaps(): ResponseEntity<List<MapDTO>> {
        return ok(mapService.getAll())
    }

    @PostMapping
    fun save(@RequestBody createMapRequest: CreateMapRequest): ResponseEntity<MapDTO> {
        return ok(mapService.save(createMapRequest))
    }

    @GetMapping("/{id}/image")
    fun getImage(@PathVariable("id") id: Long): ResponseEntity<ByteArray> {
        return ok(mapService.getImage(id))
    }

    @GetMapping("/{id}/png", produces = [MediaType.IMAGE_PNG_VALUE])
    fun getPng(@PathVariable("id") id: Long): ResponseEntity<ByteArray> {
        return ok(mapService.getImage(id))
    }

    @PatchMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody map: MapDTO): ResponseEntity<MapDTO> {
        return ok(mapService.edit(id, map))
    }

    @GetMapping("/{id}/detect-obstacles")
    fun detectObstacles(@PathVariable("id") id: Long): ResponseEntity<List<ObstacleDTO>> {
        return ok(mapService.detectObstacles(id))
    }
}
