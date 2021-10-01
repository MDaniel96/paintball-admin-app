package demo.app.paintball.data.mqtt

enum class MqttTopic(val value: String) {

    GAME("game"),

    POSITIONS_RED_TEAM("positions/redTeam"),
    POSITIONS_BLUE_TEAM("positions/blueTeam"),

    CHAT_RED_TEAM("chat/redTeam"),
    CHAT_BLUE_TEAM("chat/blueTeam");

    companion object {
        fun find(value: String): MqttTopic = values().find { it.value == value }!!
    }
}