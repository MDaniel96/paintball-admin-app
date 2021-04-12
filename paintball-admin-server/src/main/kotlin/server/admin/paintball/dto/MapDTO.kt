package server.admin.paintball.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.modelmapper.ModelMapper
import server.admin.paintball.model.Map
import server.admin.paintball.model.User

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

    @JsonIgnore
    var games: MutableSet<GameDTO> = hashSetOf(),

    @JsonIgnore
    var creator: User? = null
)

fun Map.toDTO(mapper: ModelMapper): MapDTO =
    mapper.map(this, MapDTO::class.java)
