package server.admin.paintball.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(value = ["classpath:default.properties"])
class AppConfig {

    @Value("\${map.images.location}")
    val mapImagesLocation: String = ""
}
