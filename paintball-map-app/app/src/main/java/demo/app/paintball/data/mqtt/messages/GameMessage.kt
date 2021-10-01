package demo.app.paintball.data.mqtt.messages

import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.mqtt.MqttMessage

class GameMessage(raw: String) : MqttMessage(raw) {

    var type: Type = Type.find(rawFields[1])

    constructor(type: Type) : this("${player.name}$SEPARATOR$type")

    override fun getTopic() = playerTopics.game

    enum class Type(val value: String) {
        START("START"),
        LEAVE("LEAVE");

        companion object {
            fun find(value: String): Type = values().find { it.value == value }!!
        }
    }
}