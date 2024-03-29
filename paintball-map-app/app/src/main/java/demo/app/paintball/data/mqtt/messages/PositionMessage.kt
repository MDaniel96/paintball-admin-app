package demo.app.paintball.data.mqtt.messages

import demo.app.paintball.PaintballApplication.Companion.currentUser
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.mqtt.MqttMessage

class PositionMessage(raw: String) : MqttMessage(raw) {

    var posX = rawFields[1].toInt()
    var posY = rawFields[2].toInt()

    constructor(posX: Int, posY: Int) : this("${currentUser.username}$SEPARATOR$posX$SEPARATOR$posY")

    override fun getTopic() = playerTopics.positions
}