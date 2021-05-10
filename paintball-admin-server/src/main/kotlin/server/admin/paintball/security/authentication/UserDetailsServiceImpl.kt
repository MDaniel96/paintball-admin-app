package server.admin.paintball.security.authentication

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.admin.paintball.repository.UserRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findFirstByUsername(username)?.let {
            AuthUserDetails(it)
        } ?: throw EntityNotFoundException("User not found")
    }
}
