package server.admin.paintball.service

import server.admin.paintball.dto.UserDTO
import server.admin.paintball.dto.request.RegisterUserRequest
import server.admin.paintball.model.User

interface UserService {

    fun getCurrentUser(): UserDTO

    fun getById(id: Long): UserDTO

    fun getLoggedInUser(): User

    fun registerUser(registerUserRequest: RegisterUserRequest): UserDTO

    fun getUserById(id: Long): User
}
