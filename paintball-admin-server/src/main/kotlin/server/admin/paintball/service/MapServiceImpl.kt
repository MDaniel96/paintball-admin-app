package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.dto.toDTO
import server.admin.paintball.model.Map
import server.admin.paintball.repository.MapRepository
import javax.transaction.Transactional

@Service
class MapServiceImpl(
    private val mapRepository: MapRepository,
    private val mapper: ModelMapper,
    private val locationService: LocationService,
    private val userService: UserService
) : MapService {

    @Transactional
    override fun save(createMapRequest: CreateMapRequest): MapDTO {
        createMapRequest.run {
            val map = mapRepository.save(
                Map(
                    name = name,
                    orientation = orientation,
                    location = locationService.getLocationById(locationId)
                )
            )
            val user = userService.getUserById(userId)
            user.addMapUnderCreation(map)
            return map.toDTO(mapper)
        }
    }
}
