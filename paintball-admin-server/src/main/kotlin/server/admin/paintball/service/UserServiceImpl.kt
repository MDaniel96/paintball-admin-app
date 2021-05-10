package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import server.admin.paintball.dto.UserDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.model.User
import server.admin.paintball.repository.UserRepository
import server.admin.paintball.security.authentication.AuthUserDetails
import server.admin.paintball.util.exceptions.EntityNotFoundException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val mapper: ModelMapper
) : UserService {

    override fun getCurrentUser(): UserDTO {
        return getLoggedInUser().toDTO(mapper)
    }

    override fun getById(id: Long): UserDTO {
        return getUserById(id).toDTO(mapper)
    }

    override fun getLoggedInUser(): User {
        val authUser = SecurityContextHolder.getContext().authentication.principal as AuthUserDetails
        return getUserById(authUser.user.id)
    }

    private fun getUserById(id: Long): User {
        return userRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("User not found")
    }
}
