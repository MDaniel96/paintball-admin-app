package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Obstacle

class ObstacleDTO(

    val id: Long = -1L,
    var x: Long = -1L,
    var y: Long = -1L,
    var width: Long = -1L,
    var height: Long = -1L
)

fun Set<ObstacleDTO>.toEntity(mapper: ModelMapper): Set<Obstacle> {
    val listType = object : TypeToken<Set<Obstacle>>() {}.type
    return mapper.map(this, listType)
}
