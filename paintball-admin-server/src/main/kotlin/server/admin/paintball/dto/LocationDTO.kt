package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Location

class LocationDTO(

    val id: Long = -1L,
    var name: String = ""
)

fun List<Location>.toDTO(mapper: ModelMapper): List<LocationDTO> {
    val listType = object : TypeToken<List<LocationDTO>>() {}.type
    return mapper.map(this, listType)
}
