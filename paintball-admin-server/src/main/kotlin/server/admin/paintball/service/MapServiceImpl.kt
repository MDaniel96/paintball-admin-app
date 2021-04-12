package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.dto.toDTO
import server.admin.paintball.dto.toEntity
import server.admin.paintball.model.Map
import server.admin.paintball.repository.MapRepository
import server.admin.paintball.repository.ObstacleRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException
import java.io.File
import java.util.*
import javax.transaction.Transactional

@Service
class MapServiceImpl(
    private val mapRepository: MapRepository,
    private val obstacleRepository: ObstacleRepository,
    private val mapper: ModelMapper,
    private val locationService: LocationService,
    private val userService: UserService
) : MapService {

    @Value("\${map.images.location}")
    private val mapImagesLocation: String = ""

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
            saveImage(imageBase64, map.id)
            val user = userService.getUserById(userId)
            user.addMapUnderCreation(map)
            return map.toDTO(mapper)
        }
    }

    override fun getImage(id: Long): ByteArray {
        return File("${mapImagesLocation}/$id.png")
            .readBytes()
    }

    @Transactional
    override fun edit(id: Long, map: MapDTO): MapDTO {

        val obstacles = obstacleRepository.saveAll(
            map.obstacles.toEntity(mapper)
        )

        return getMapById(id).also {
            if (map.borderX != -1L) {
                it.borderX = map.borderX
                it.borderY = map.borderY
                it.borderWidth = map.borderWidth
                it.borderHeight = map.borderHeight
            }
        }.also {
            it.addObstacles(obstacles.toSet())
        }.run {
            toDTO(mapper)
        }
    }

    private fun saveImage(imageBase64: String, id: Long) {
        val imageByteArray = Base64.getDecoder().decode(imageBase64)
        File("${mapImagesLocation}/$id.png")
            .writeBytes(imageByteArray)
    }

    private fun getMapById(id: Long): Map {
        return mapRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Map not found")
    }
}
