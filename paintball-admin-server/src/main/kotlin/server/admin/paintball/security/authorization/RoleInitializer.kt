package server.admin.paintball.security.authorization

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import server.admin.paintball.model.Role
import server.admin.paintball.repository.RoleRepository

@Component
@Order(1)
class RoleInitializer(
    private val roleRepository: RoleRepository
) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments?) {
        RoleType.values().forEach { roleType ->
            if (!roleRepository.existsByName(roleType)) {
                roleRepository.save(
                    Role(name = roleType)
                )
            }
        }
    }
}
