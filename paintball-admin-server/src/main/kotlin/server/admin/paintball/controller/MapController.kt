package server.admin.paintball.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.AnchorDTO
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
    @PreAuthorize("hasRole('ADMIN')")
    fun save(@RequestBody createMapRequest: CreateMapRequest): ResponseEntity<MapDTO> {
        return ok(mapService.save(createMapRequest))
    }

    @GetMapping("/{id}/image")
    fun getImage(@PathVariable("id") id: Long): ResponseEntity<ByteArray> {
        return ok(mapService.getImage(id))
    }

    @GetMapping("/{id}/jpeg", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getImageJpeg(@PathVariable("id") id: Long): ResponseEntity<ByteArray> {
        return ok(mapService.getImage(id))
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun edit(@PathVariable id: Long, @RequestBody map: MapDTO): ResponseEntity<MapDTO> {
        return ok(mapService.edit(id, map))
    }

    @PostMapping("{id}/finish-edit")
    @PreAuthorize("hasRole('ADMIN')")
    fun finishEdit(@PathVariable id: Long): ResponseEntity<MapDTO> {
        return ok(mapService.finishEdit(id))
    }

    @GetMapping("/{id}/detect-obstacles")
    @PreAuthorize("hasRole('ADMIN')")
    fun detectObstacles(@PathVariable("id") id: Long): ResponseEntity<List<ObstacleDTO>> {
        return ok(mapService.detectObstacles(id))
    }

    @GetMapping("/{id}/calculate-anchors/{anchorRadius}")
    @PreAuthorize("hasRole('ADMIN')")
    fun calculateAnchors(
        @PathVariable("id") id: Long,
        @PathVariable("anchorRadius") anchorRadius: Int
    ): ResponseEntity<List<AnchorDTO>> {
        return ok(mapService.calculateAnchors(id, anchorRadius))
    }
}
