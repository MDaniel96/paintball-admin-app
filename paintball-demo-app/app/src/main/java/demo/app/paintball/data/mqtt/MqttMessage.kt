package demo.app.paintball.data.mqtt

abstract class MqttMessage(private val raw: String) {

    companion object {
        const val SEPARATOR = '|'
    }

    val rawFields = raw.split(SEPARATOR)

    var playerName = rawFields[0]

    fun publish(mqttService: MqttService) {
        mqttService.publish(getTopic(), raw)
    }

    abstract fun getTopic(): MqttTopic
}