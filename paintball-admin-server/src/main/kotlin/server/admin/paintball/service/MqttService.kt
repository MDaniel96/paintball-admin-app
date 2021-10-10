package server.admin.paintball.service

interface MqttService {

    fun publish(topic: String, message: String)
}