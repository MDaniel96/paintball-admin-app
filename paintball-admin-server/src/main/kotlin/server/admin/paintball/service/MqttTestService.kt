package server.admin.paintball.service

interface MqttTestService {

    fun sendPositionMessages(gameId: Long)

    fun stopPositionMessages(gameId: Long)

    fun sendLeaveGameMessage(playerName: String)

    fun sendChatMessage(playerName: String)
}