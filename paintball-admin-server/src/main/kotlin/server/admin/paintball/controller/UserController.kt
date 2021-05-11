package server.admin.paintball.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import server.admin.paintball.dto.UserDTO
import server.admin.paintball.dto.request.RegisterUserRequest
import server.admin.paintball.service.UserService

@RestController
@RequestMapping(UserController.BASE_URL)
class UserController(private val userService: UserService) {

    companion object {
        const val BASE_URL = "/api/user"
    }

    @GetMapping("/current")
    fun getCurrentUser(): ResponseEntity<UserDTO> {
        return ok(userService.getCurrentUser())
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<UserDTO> {
        return ok(userService.registerUser(registerUserRequest))
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDTO> {
        return ok(userService.getById(id))
    }
}
