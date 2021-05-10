package server.admin.paintball.model

import org.springframework.security.core.authority.SimpleGrantedAuthority
import server.admin.paintball.security.authorization.RoleType
import javax.persistence.*

@Entity
class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    var name: RoleType = RoleType.PLAYER
) {

    val grantedAuthority: SimpleGrantedAuthority
        get() = SimpleGrantedAuthority("ROLE_$name")
}
