package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import server.admin.paintball.dto.LocationDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.repository.LocationRepository

@Service
class LocationServiceImpl(
    private val locationRepository: LocationRepository,
    private val mapper: ModelMapper
) : LocationService {

    override fun getLocations(): List<LocationDTO> {
        return locationRepository.findAll().toDTO(mapper)
    }
}
