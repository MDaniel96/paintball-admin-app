package server.admin.paintball.service

import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import server.admin.paintball.config.AppConfig
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

@Service
class MqttTestServiceImpl(val gameService: GameService,
                          val mqttService: MqttService,
                          val resourceLoader: ResourceLoader
) : MqttTestService {

    companion object {
        const val GAME = "game"
        const val RED_TEAM_TOPIC = "positions/redTeam"
        const val BLUE_TEAM_TOPIC = "positions/blueTeam"
        const val RED_TEAM_CHAT = "chat/redTeam"
        const val BLUE_TEAM_CHAT = "chat/blueTeam"
    }

    private lateinit var timer: Timer

    override fun sendPositionMessages(gameId: Long) {
        val blueStartX = 0
        val redStartX = 60
        val rangeFrom = -10L
        val rangeTo = 200L
        val blueStartY = 30
        val redStartY = 50

        val game = gameService.getGame(gameId)
        val redTeam = game.redPlayers
        val blueTeam = game.bluePlayers

        timer = Timer()
        redTeam.forEach {
            val testPositions = getPositions(it.username, blueStartX + Random.nextLong(rangeFrom, rangeTo).toInt(),
                    blueStartY + Random.nextLong(rangeFrom, rangeTo).toInt(), 10)
            var i = 0
            timer.schedule(40L, Random.nextLong(50, 150)) {
                if (i < testPositions.size - 1) i++ else i = 0
                mqttService.publish(
                        topic = RED_TEAM_TOPIC,
                        message = testPositions[i]
                )
            }
        }
        blueTeam.forEach {
            val testPositions = getPositions(it.username, redStartX + Random.nextLong(rangeFrom, rangeTo).toInt(),
                    redStartY + Random.nextLong(rangeFrom, rangeTo).toInt(), 10)
            var i = 0
            timer.schedule(40L, Random.nextLong(50, 150)) {
                if (i < testPositions.size - 1) i++ else i = 0
                mqttService.publish(
                        topic = BLUE_TEAM_TOPIC,
                        message = testPositions[i]
                )
            }
        }
    }

    override fun stopPositionMessages(gameId: Long) {
        timer.cancel()
    }

    override fun sendLeaveGameMessage(playerName: String) {
        mqttService.publish(topic = GAME, message = "$playerName|LEAVE")
    }

    override fun sendChatMessage(playerName: String) {
        val resource = resourceLoader.getResource("classpath:test-chat-message.txt")
        val message = resource.file.readLines()[0]
        mqttService.publish(topic = RED_TEAM_CHAT, message = "$playerName|$message|1800")
        mqttService.publish(topic = BLUE_TEAM_CHAT, message = "$playerName|$message|1800")
    }

    private fun getPositions(playerName: String, startX: Int, startY: Int, step: Int): List<String> {
        val positions = mutableListOf<String>()
        val randomRange = Random.nextInt(200, 400)
        var startX_ = startX
        var startY_ = startY

        for (pos in startX..startX + randomRange step step) {
            positions.add("$playerName|$pos|$startY")
            startX_ = pos
        }
        for (pos in startY..startY + randomRange step step) {
            positions.add("$playerName|$startX_|$pos")
            startY_ = pos
        }
        for (pos in startX_ downTo startX step step) {
            positions.add("$playerName|$pos|$startY_")
        }
        for (pos in startY_ downTo startY step step) {
            positions.add("$playerName|$startX|$pos")
        }
        return positions
    }
}