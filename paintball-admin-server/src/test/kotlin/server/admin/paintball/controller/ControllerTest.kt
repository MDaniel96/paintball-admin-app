package server.admin.paintball.controller

import io.restassured.RestAssured
import io.restassured.authentication.FormAuthConfig
import io.restassured.authentication.FormAuthScheme
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.web.server.LocalServerPort
import server.admin.paintball.security.SecurityConfig

abstract class ControllerTest {

    @LocalServerPort
    private var port = 0

    protected abstract val apiUrl: String

    @BeforeEach
    fun restAssuredSetup() {
        RestAssured.port = port
        RestAssured.basePath = "/paintball-admin$apiUrl"
        RestAssured.authentication = getAuthScheme()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    private fun getAuthScheme(): FormAuthScheme {
        return FormAuthScheme().apply {
            userName = "admin"
            password = "admin"
            config = FormAuthConfig("paintball-admin" + SecurityConfig.LOGIN_URL, "username", "password")
        }
    }
}