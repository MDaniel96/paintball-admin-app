package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Game
import java.time.LocalDateTime

class GameDTO(

    val id: Long = -1L,
    var name: String = "",
    var type: String = "",
    var state: Game.State = Game.State.CREATED,
    var date: LocalDateTime = LocalDateTime.now()
)

fun List<Game>.toDTO(mapper: ModelMapper): List<GameDTO> {
    val listType = object : TypeToken<List<GameDTO>>() {}.type
    return mapper.map(this, listType)
}
