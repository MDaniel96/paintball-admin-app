package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.admin.paintball.model.Role
import server.admin.paintball.security.authorization.RoleType

interface RoleRepository : JpaRepository<Role, Long> {

    fun existsByName(name: RoleType): Boolean

    fun findFirstByName(name: RoleType): Role?
}
