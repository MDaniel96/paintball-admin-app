package server.admin.paintball.service

import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest

interface MapService {

    fun save(createMapRequest: CreateMapRequest): MapDTO

    fun getImage(imageName: String): ByteArray

    fun edit(id: Long, map: MapDTO): MapDTO
}
