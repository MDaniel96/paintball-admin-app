package server.admin.paintball.dto

import com.fasterxml.jackson.annotation.JsonIgnore

class MapDTO(

    val id: Long = -1L,
    var name: String = "",
    var location: LocationDTO? = null,

    @JsonIgnore
    var games: MutableSet<GameDTO> = hashSetOf()
)
