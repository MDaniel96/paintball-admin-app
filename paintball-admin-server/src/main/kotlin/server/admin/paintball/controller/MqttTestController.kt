package server.admin.paintball.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import server.admin.paintball.service.MqttTestService

@RestController
@RequestMapping(MqttTestController.BASE_URL)
class MqttTestController(private val mqttTestService: MqttTestService) {

    companion object {
        const val BASE_URL = "/api/mqtt/test"
    }

    @PostMapping("/game/{gameId}/positions/start")
    fun sendPositionMessages(@PathVariable("gameId") gameId: Long) {
        mqttTestService.sendPositionMessages(gameId)
    }

    @PostMapping("/game/{gameId}/positions/stop")
    fun stopPositionMessages(@PathVariable("gameId") gameId: Long) {
        mqttTestService.stopPositionMessages(gameId)
    }

    @PostMapping("/leave-game/{playerName}")
    fun sendLeaveGameMessage(@PathVariable playerName: String) {
        mqttTestService.sendLeaveGameMessage(playerName)
    }

    @PostMapping("/chat-message/{playerName}")
    fun sendChatMessage(@PathVariable playerName: String) {
        mqttTestService.sendChatMessage(playerName)
    }
}