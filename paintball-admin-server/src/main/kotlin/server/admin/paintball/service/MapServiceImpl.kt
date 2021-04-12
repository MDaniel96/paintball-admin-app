package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import server.admin.paintball.config.AppConfig
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.ObstacleDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.dto.toDTO
import server.admin.paintball.dto.toEntity
import server.admin.paintball.model.Map
import server.admin.paintball.repository.AnchorRepository
import server.admin.paintball.repository.MapRepository
import server.admin.paintball.repository.ObstacleRepository
import server.admin.paintball.util.detectors.ObstacleDetector
import server.admin.paintball.util.exceptions.BadRequestException
import server.admin.paintball.util.exceptions.EntityNotFoundException
import java.io.File
import java.util.*
import javax.transaction.Transactional

@Service
class MapServiceImpl(
    private val appConfig: AppConfig,
    private val mapRepository: MapRepository,
    private val obstacleRepository: ObstacleRepository,
    private val anchorRepository: AnchorRepository,
    private val mapper: ModelMapper,
    private val locationService: LocationService,
    private val userService: UserService,
    private val obstacleDetector: ObstacleDetector
) : MapService {

    @Transactional
    override fun save(createMapRequest: CreateMapRequest): MapDTO {
        return createMapRequest.run {
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
            map.toDTO(mapper)
        }
    }

    override fun getImage(id: Long): ByteArray {
        return File("${appConfig.mapImagesLocation}/$id.png")
            .readBytes()
    }

    @Transactional
    override fun edit(id: Long, map: MapDTO): MapDTO {
        val obstacles = obstacleRepository.saveAll(
            map.obstacles.toEntity(mapper)
        )
        val anchors = anchorRepository.saveAll(
            map.anchors.toEntity(mapper)
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
            it.addAnchors(anchors.toSet())
        }.run {
            toDTO(mapper)
        }
    }

    override fun detectObstacles(id: Long): List<ObstacleDTO> {
        val map = getMapById(id)
        if (map.borderX == -1L) {
            throw BadRequestException("Map border is not set")
        }
        return obstacleDetector.detectObstacles(map.toDTO(mapper))
    }

    private fun saveImage(imageBase64: String, id: Long) {
        val imageByteArray = Base64.getDecoder().decode(imageBase64)
        File("${appConfig.mapImagesLocation}/$id.png")
            .writeBytes(imageByteArray)
    }

    private fun getMapById(id: Long): Map {
        return mapRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Map not found")
    }
}
