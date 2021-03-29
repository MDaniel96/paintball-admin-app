package server.admin.paintball.dto

import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import server.admin.paintball.model.Game
import java.time.LocalDate

class GameDTO(

    val id: Long = -1L,
    var name: String = "",
    var type: String = "",
    var state: Game.State = Game.State.CREATED,
    var date: LocalDate = LocalDate.now(),
    var redPlayers: MutableSet<UserDTO> = hashSetOf(),
    var bluePlayers: MutableSet<UserDTO> = hashSetOf()
)

fun List<Game>.toDTO(mapper: ModelMapper): List<GameDTO> {
    val listType = object : TypeToken<List<GameDTO>>() {}.type
    return mapper.map(this, listType)
}
