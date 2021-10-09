package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.User

class UserDTO(

    val id: Long = -1L,
    var username: String = "",
    var mapsUnderCreation: MutableSet<MapDTO> = hashSetOf(),
    var roles: MutableSet<RoleDTO> = hashSetOf()
)

fun List<User>.toDTO(mapper: ModelMapper): List<UserDTO> {
    val listType = object : TypeToken<List<UserDTO>>() {}.type
    return mapper.map(this, listType)
}

fun User.toDTO(mapper: ModelMapper): UserDTO =
    mapper.map(this, UserDTO::class.java)
