package server.admin.paintball.service

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence
import org.springframework.stereotype.Service
import server.admin.paintball.config.AppConfig

@Service
class MqttServiceImpl(
        private val appConfig: AppConfig
) : MqttService {

    private lateinit var mqttClient: MqttClient

    init {
        if (appConfig.isMqttEnabled) {
            connect()
        }
    }

    private fun connect() {
        mqttClient = MqttClient(
                appConfig.mqttBrokerUrl,
                MqttClient.generateClientId(),
                MqttDefaultFilePersistence("/tmp")
        )
        mqttClient.connect()
    }

    override fun publish(topic: String, message: String) {
        if (appConfig.isMqttEnabled) {
            val mqttMessage = MqttMessage()
            mqttMessage.payload = message.toByteArray()
            mqttClient.publish(topic, mqttMessage)
        }
    }
}