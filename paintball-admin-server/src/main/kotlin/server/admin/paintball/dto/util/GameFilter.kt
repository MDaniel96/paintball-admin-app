package server.admin.paintball.dto.util

import server.admin.paintball.model.Game
import java.time.LocalDate

class GameFilter(

    val state: Game.State? = null,
    val name: String? = null,
    val types: List<String>? = null,
    val date: LocalDate? = null
)
