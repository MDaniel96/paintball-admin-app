package server.admin.paintball.util.detectors

import org.springframework.stereotype.Service
import server.admin.paintball.config.AppConfig
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.ObstacleDTO
import java.io.File

@Service
class ObstacleDetectorImpl(
    private val appConfig: AppConfig
) : ObstacleDetector {

    override fun detectObstacles(map: MapDTO): List<ObstacleDTO> {

        // for testing call: http://localhost:8080/paintball-admin/api/map/1/detect-obstacles

        // map 1 image path and image in bytes
        val path = "${appConfig.mapImagesLocation}/${map.id}.png"
        val bytes = File(path).readBytes()

        // map 1 border rectangle
        println("top left corner: (${map.borderX}, ${map.borderY})")
        println("width, height  : (${map.borderWidth}, ${map.borderHeight})")

        // TODO

        return listOf(
            ObstacleDTO(x = 10, y = 10, width = 10, height = 10),
            ObstacleDTO(x = 20, y = 20, width = 20, height = 20),
            ObstacleDTO(x = 30, y = 30, width = 30, height = 30)
        )
    }
}
