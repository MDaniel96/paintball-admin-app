package server.admin.paintball.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Location

class LocationDTO(

    val id: Long = -1L,
    var name: String = "",

    @JsonIgnore
    var maps: MutableSet<MapDTO> = hashSetOf()
)

fun List<Location>.toDTO(mapper: ModelMapper): List<LocationDTO> {
    val listType = object : TypeToken<List<LocationDTO>>() {}.type
    return mapper.map(this, listType)
}
