package server.admin.paintball.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Anchor

class AnchorDTO(

    val id: Long = -1L,
    var x: Long = -1L,
    var y: Long = -1L,
    var radius: Long = -1L,

    @JsonIgnore
    var map: MapDTO? = null
)

fun Set<AnchorDTO>.toEntity(mapper: ModelMapper): Set<Anchor> {
    val listType = object : TypeToken<Set<Anchor>>() {}.type
    return mapper.map(this, listType)
}
