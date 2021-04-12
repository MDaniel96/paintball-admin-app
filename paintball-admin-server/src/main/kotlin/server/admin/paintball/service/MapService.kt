package server.admin.paintball.service

import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest

interface MapService {

    fun save(createMapRequest: CreateMapRequest): MapDTO
}
