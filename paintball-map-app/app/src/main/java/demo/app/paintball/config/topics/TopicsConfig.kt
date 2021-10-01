package demo.app.paintball.config.topics

import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.data.mqtt.MqttTopic
import demo.app.paintball.data.rest.models.Player

abstract class TopicsConfig {

    companion object {
        val playerTopics
            get() = when (player.team) {
                Player.Team.RED -> RedTeamTopics
                Player.Team.BLUE -> BlueTeamTopics
                else -> RedTeamTopics
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