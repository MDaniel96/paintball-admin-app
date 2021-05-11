package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import server.admin.paintball.config.AppConfig
import server.admin.paintball.dto.*
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.model.Map
import server.admin.paintball.repository.AnchorRepository
import server.admin.paintball.repository.MapRepository
import server.admin.paintball.repository.ObstacleRepository
import server.admin.paintball.util.detectors.AnchorCalculator
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
    private val obstacleDetector: ObstacleDetector,
    private val anchorCalculator: AnchorCalculator
) : MapService {

    override fun getAll(): List<MapDTO> {
        return mapRepository.findAll().toDTO(mapper)
    }

    @Transactional
    override fun save(createMapRequest: CreateMapRequest): MapDTO {
        return createMapRequest.run {
            val user = userService.getLoggedInUser()
            val map = mapRepository.save(
                Map(
                    name = name,
                    orientation = orientation,
                    location = locationService.getLocationById(locationId),
                    creator = user,
                    width = width,
                    height = height
                )
            )
            saveImage(imageBase64, map.id)
            user.mapsUnderCreation.add(map)
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
            if (obstacles.isNotEmpty()) {
                obstacles.forEach { obstacle -> obstacle.map = it }
                it.obstacles.addAll(obstacles.toSet())
            }
            if (anchors.isNotEmpty()) {
                anchors.forEach { anchor -> anchor.map = it }
                it.anchors.addAll(anchors.toSet())
            }
            if (map.anchorRadiusInPixels != -1L) {
                it.anchorRadiusInPixels = map.anchorRadiusInPixels
            }
            if (map.anchorRadiusInMm != -1L) {
                it.anchorRadiusInMm = map.anchorRadiusInMm
            }
        }.run {
            toDTO(mapper)
        }
    }

    @Transactional
    override fun finishEdit(id: Long): MapDTO {
        return getMapById(id).also { map ->
            if (map.name == "" || map.borderX == -1L || map.obstacles.size == 0 || map.anchors.size == 0) {
                throw BadRequestException("Map can't be finished yet, there are unset fields")
            }
        }.also { map ->
            map.creator?.mapsUnderCreation = hashSetOf()
            map.creator = null
        }.run {
            this.toDTO(mapper)
        }
    }

    override fun detectObstacles(id: Long): List<ObstacleDTO> {
        val map = getMapById(id)
        if (map.borderX == -1L) {
            throw BadRequestException("Map border is not set")
        }
        return obstacleDetector.detectObstacles(map.toDTO(mapper))
    }

    override fun calculateAnchors(id: Long, anchorRadius: Int): List<AnchorDTO> {
        val map = getMapById(id)
        if (map.borderX == -1L) {
            throw BadRequestException("Map border is not set")
        }
        return anchorCalculator.calculateAnchors(map.toDTO(mapper), anchorRadius)
    }

    override fun getMapById(id: Long): Map {
        return mapRepository.findByIdOrNull(id)
                ?: throw EntityNotFoundException("Map not found")
    }

    private fun saveImage(imageBase64: String, id: Long) {
        val imageByteArray = Base64.getDecoder().decode(imageBase64)
        File("${appConfig.mapImagesLocation}/$id.png")
            .writeBytes(imageByteArray)
    }
}
