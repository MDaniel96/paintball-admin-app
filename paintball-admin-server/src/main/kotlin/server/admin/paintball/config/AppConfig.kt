package server.admin.paintball.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(value = ["classpath:default.properties"])
class AppConfig {

    @Value("\${map.images.location}")
    val mapImagesLocation: String = ""

    @Value("\${api.protected}")
    val isApiProtected: Boolean = true

    @Value("\${mqtt.enabled}")
    val isMqttEnabled: Boolean = true

    @Value("\${mqtt.broker.url}")
    val mqttBrokerUrl = ""
}
