package server.admin.paintball.util.detectors

import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.ObstacleDTO

interface ObstacleDetector {

    fun detectObstacles(map: MapDTO): List<ObstacleDTO>
}
