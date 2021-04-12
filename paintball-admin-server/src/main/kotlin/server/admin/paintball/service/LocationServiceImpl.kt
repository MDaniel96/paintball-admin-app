package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import server.admin.paintball.dto.LocationDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.model.Location
import server.admin.paintball.repository.LocationRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException

@Service
class LocationServiceImpl(
    private val locationRepository: LocationRepository,
    private val mapper: ModelMapper
) : LocationService {

    override fun getLocations(): List<LocationDTO> {
        return locationRepository.findAll().toDTO(mapper)
    }

    override fun getLocationById(id: Long): Location {
        return locationRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Location not found")
    }
}
