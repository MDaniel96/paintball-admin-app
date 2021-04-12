package server.admin.paintball.service

import server.admin.paintball.model.User

interface UserService {

    fun getUserById(id: Long): User
}
