package server.admin.paintball.dto

class UserDTO(

    val id: Long = -1L,
    var name: String = "",
    var mapsUnderCreation: MutableSet<MapDTO> = hashSetOf(),
)
