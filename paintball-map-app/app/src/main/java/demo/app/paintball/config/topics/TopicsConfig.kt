package demo.app.paintball.config.topics

import demo.app.paintball.PaintballApplication.Companion.currentTeam
import demo.app.paintball.data.mqtt.MqttTopic
import demo.app.paintball.data.rest.enums.Team

abstract class TopicsConfig {

    companion object {
        val playerTopics
            get() = when (currentTeam) {
                Team.RED -> RedTeamTopics
                Team.BLUE -> BlueTeamTopics
            }
    }

    /*
    Game topic
     */
    val game = MqttTopic.GAME

    /*
    Team's and enemy team's position topic
     */
    abstract val positions: MqttTopic
    abstract val enemyPositions: MqttTopic

    /*
    Team's and enemy team's chat topic
    */
    abstract val teamChat: MqttTopic
    abstract val enemyTeamChat: MqttTopic
}