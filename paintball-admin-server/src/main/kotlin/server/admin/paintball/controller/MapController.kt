package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.service.MapService

@RestController
@RequestMapping(MapController.BASE_URL)
class MapController(private val mapService: MapService) {

    companion object {
        const val BASE_URL = "/api/map"
    }

    @PostMapping
    fun save(@RequestBody createMapRequest: CreateMapRequest): ResponseEntity<MapDTO> {
        return ok(mapService.save(createMapRequest))
    }

    @GetMapping("/{id}/image")
    fun getImage(@PathVariable("id") id: Long): ResponseEntity<ByteArray> {
        return ok(mapService.getImage(id))
    }

    @PatchMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody map: MapDTO): ResponseEntity<MapDTO> {
        return ok(mapService.edit(id, map))
    }
}
