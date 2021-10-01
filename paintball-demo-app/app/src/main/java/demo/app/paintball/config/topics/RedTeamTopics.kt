package demo.app.paintball.config.topics

import demo.app.paintball.data.mqtt.MqttTopic

object RedTeamTopics : TopicsConfig() {

    override val positions = MqttTopic.POSITIONS_RED_TEAM
    override val enemyPositions = MqttTopic.POSITIONS_BLUE_TEAM

    override val teamChat = MqttTopic.CHAT_RED_TEAM
    override val enemyTeamChat = MqttTopic.CHAT_BLUE_TEAM
}