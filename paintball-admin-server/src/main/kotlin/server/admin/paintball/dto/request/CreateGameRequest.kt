package server.admin.paintball.dto.request

import server.admin.paintball.model.Game.LocalizationMode

class CreateGameRequest(
        val name: String = "",
        val type: String = "",
        val mapId: Long = -1L,
        val localizationMode: LocalizationMode
)
