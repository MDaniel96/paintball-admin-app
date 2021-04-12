package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import server.admin.paintball.dto.LocationDTO
import server.admin.paintball.service.LocationService

@RestController
@RequestMapping(LocationController.BASE_URL)
class LocationController(private val locationService: LocationService) {

    companion object {
        const val BASE_URL = "/api/location"
    }

    @GetMapping
    fun getLocations(): ResponseEntity<List<LocationDTO>> {
        return ok(locationService.getLocations())
    }
}
