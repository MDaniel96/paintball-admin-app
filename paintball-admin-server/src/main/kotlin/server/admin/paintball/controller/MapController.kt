package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
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

    @PostMapping("/uploadImage")
    fun uploadImage(imageFile: MultipartFile) {
        TODO()
    }
}
