package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import server.admin.paintball.model.User

class UserDTO(

    val id: Long = -1L,
    var name: String = "",
    var mapsUnderCreation: MutableSet<MapDTO> = hashSetOf(),
)

fun User.toDTO(mapper: ModelMapper): UserDTO =
    mapper.map(this, UserDTO::class.java)
