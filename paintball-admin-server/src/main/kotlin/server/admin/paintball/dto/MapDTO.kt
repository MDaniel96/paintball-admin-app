package server.admin.paintball.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.modelmapper.ModelMapper
import server.admin.paintball.model.Map
import server.admin.paintball.model.User

class MapDTO(

    val id: Long = -1L,
    var name: String = "",
    var imageUrl: String = "",
    var orientation: Int = 0,
    var location: LocationDTO? = null,

    @JsonIgnore
    var games: MutableSet<GameDTO> = hashSetOf(),

    @JsonIgnore
    var creator: User? = null
)

fun Map.toDTO(mapper: ModelMapper): MapDTO =
    mapper.map(this, MapDTO::class.java)
