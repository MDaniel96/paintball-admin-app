package server.admin.paintball.service

import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.ObstacleDTO
import server.admin.paintball.dto.request.CreateMapRequest

interface MapService {

    fun getAll(): List<MapDTO>

    fun save(createMapRequest: CreateMapRequest): MapDTO

    fun getImage(id: Long): ByteArray

    fun edit(id: Long, map: MapDTO): MapDTO

    fun detectObstacles(id: Long): List<ObstacleDTO>
}
