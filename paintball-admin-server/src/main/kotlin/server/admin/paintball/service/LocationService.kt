package server.admin.paintball.service

import server.admin.paintball.dto.LocationDTO
import server.admin.paintball.model.Location

interface LocationService {

    fun getLocations(): List<LocationDTO>

    fun getLocationById(id: Long): Location
}
