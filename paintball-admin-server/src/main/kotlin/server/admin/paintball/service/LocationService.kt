package server.admin.paintball.service

import server.admin.paintball.dto.LocationDTO

interface LocationService {

    fun getLocations(): List<LocationDTO>
}
