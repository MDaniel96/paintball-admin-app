package demo.app.paintball.data.mqtt

import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.data.mqtt.messages.ChatMessage
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.mqtt.messages.PositionMessage
import demo.app.paintball.util.toast
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MqttServiceImpl @Inject constructor() : MqttService {

    override var gameListener: MqttService.GameListener? = null
    override var positionListener: MqttService.PositionListener? = null
    override var chatListener: MqttService.ChatListener? = null

    private var mqttAndroidClient: MqttAndroidClient =
        MqttAndroidClient(context, "tcp://${context.getString(R.string.serverURL)}:1883", MqttClient.generateClientId())

    init {
        setCallback()
        connect()
    }

    private fun setCallback() {
        mqttAndroidClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                gameListener?.connectComplete()
            }

            override fun messageArrived(topic: String, mqttMessage: org.eclipse.paho.client.mqttv3.MqttMessage) {
                val rawMessage = mqttMessage.toString()
                when (MqttTopic.find(topic)) {
                    MqttTopic.GAME -> {
                        gameListener?.gameMessageArrived(GameMessage(raw = rawMessage))
                    }
                    MqttTopic.POSITIONS_RED_TEAM, MqttTopic.POSITIONS_BLUE_TEAM -> {
                        positionListener?.positionMessageArrived(PositionMessage(raw = rawMessage))
                    }
                    MqttTopic.CHAT_RED_TEAM, MqttTopic.CHAT_BLUE_TEAM -> {
                        chatListener?.chatMessageArrived(ChatMessage(raw = rawMessage))
                    }
                }
            }

            override fun connectionLost(throwable: Throwable) {}
            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {}
        })
    }

    private fun connect() {
        val mqttConnectOptions = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
        }

        mqttAndroidClient.connect(mqttConnectOptions, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken) {
                toast("Connected to MQTT broker")
            }

            override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                toast("MQTT: Failed to connect to: ${context.getString(R.string.serverURL)}")
            }
        })
    }

    override fun subscribe(topic: MqttTopic) {
        mqttAndroidClient.subscribe(topic.value, 0, null)
    }

    override fun unsubscribe(topic: MqttTopic) {
        mqttAndroidClient.unsubscribe(topic.value)
    }

    override fun publish(topic: MqttTopic, message: String) {
        mqttAndroidClient.publish(topic.value, message.toByteArray(), 0, false)
    }
}