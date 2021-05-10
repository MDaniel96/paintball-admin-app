package server.admin.paintball.model

import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false, unique = true)
    var username: String = "",

    @Column(nullable = false)
    var password: String = "",

    @OneToMany(mappedBy = "creator")
    var mapsUnderCreation: MutableSet<Map> = hashSetOf(),

    @ManyToMany(mappedBy = "redPlayers")
    var redGames: MutableSet<Game> = hashSetOf(),

    @ManyToMany(mappedBy = "bluePlayers")
    var blueGames: MutableSet<Game> = hashSetOf(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    var roles: MutableSet<Role> = hashSetOf(),
)
