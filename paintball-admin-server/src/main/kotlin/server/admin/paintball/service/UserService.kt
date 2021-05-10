package server.admin.paintball.service

import server.admin.paintball.dto.UserDTO
import server.admin.paintball.model.User

interface UserService {

    fun getCurrentUser(): UserDTO

    fun getById(id: Long): UserDTO

    fun getLoggedInUser(): User
}
