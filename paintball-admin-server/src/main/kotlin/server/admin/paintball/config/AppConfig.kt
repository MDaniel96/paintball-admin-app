package server.admin.paintball.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppConfig {

    @Value("\${map.images.location}")
    val mapImagesLocation: String = ""
}
