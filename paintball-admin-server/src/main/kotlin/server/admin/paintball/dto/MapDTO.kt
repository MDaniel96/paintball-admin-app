package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Map

class MapDTO(

    val id: Long = -1L,
    var name: String = "",
    var orientation: Int = 0,
    var borderX: Long = -1L,
    var borderY: Long = -1L,
    var borderWidth: Long = -1L,
    var borderHeight: Long = -1L,
    var location: LocationDTO? = null,
    var obstacles: MutableSet<ObstacleDTO> = hashSetOf(),
    var anchors: MutableSet<AnchorDTO> = hashSetOf()
)

fun Map.toDTO(mapper: ModelMapper): MapDTO =
    mapper.map(this, MapDTO::class.java)

fun List<Map>.toDTO(mapper: ModelMapper): List<MapDTO> {
    val listType = object : TypeToken<List<MapDTO>>() {}.type
    return mapper.map(this, listType)
}
