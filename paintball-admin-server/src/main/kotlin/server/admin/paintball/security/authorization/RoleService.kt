package server.admin.paintball.security.authorization

import org.springframework.stereotype.Service
import server.admin.paintball.model.Role
import server.admin.paintball.repository.RoleRepository

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {

    val admin: Role by lazy {
        roleRepository.findFirstByName(RoleType.ADMIN)!!
    }

    val player: Role by lazy {
        roleRepository.findFirstByName(RoleType.PLAYER)!!
    }
}
