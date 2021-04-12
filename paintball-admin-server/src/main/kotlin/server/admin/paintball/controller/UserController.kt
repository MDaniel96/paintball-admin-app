package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import server.admin.paintball.dto.UserDTO
import server.admin.paintball.service.UserService

@RestController
@RequestMapping(UserController.BASE_URL)
class UserController(private val userService: UserService) {

    companion object {
        const val BASE_URL = "/api/user"
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDTO> {
        return ok(userService.getById(id))
    }
}
