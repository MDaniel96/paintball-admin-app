package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.admin.paintball.model.User

interface UserRepository : JpaRepository<User, Long> {

    fun findFirstByUsername(username: String): User?
}
