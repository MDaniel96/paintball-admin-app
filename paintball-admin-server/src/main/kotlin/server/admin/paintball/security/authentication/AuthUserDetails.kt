package server.admin.paintball.security.authentication

import org.springframework.security.core.userdetails.UserDetails
import server.admin.paintball.model.User

class AuthUserDetails(val user: User) : UserDetails {

    override fun getAuthorities() = user.roles.map { it.grantedAuthority }

    override fun getPassword() = user.password

    override fun getUsername() = user.username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
