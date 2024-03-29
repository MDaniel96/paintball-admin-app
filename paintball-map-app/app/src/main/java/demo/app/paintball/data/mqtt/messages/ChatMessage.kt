package demo.app.paintball.data.mqtt.messages

import demo.app.paintball.PaintballApplication.Companion.currentUser
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.mqtt.MqttMessage

class ChatMessage(raw: String) : MqttMessage(raw) {

    var message = rawFields[1]
    var length = rawFields[2].toLong()

    constructor(message: String, length: Long) : this("${currentUser.username}$SEPARATOR$message$SEPARATOR$length")

    override fun getTopic() = playerTopics.teamChat
}