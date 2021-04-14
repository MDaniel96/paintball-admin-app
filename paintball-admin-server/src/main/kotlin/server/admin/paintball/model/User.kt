package server.admin.paintball.model

import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    var name: String = "",

    @OneToMany(mappedBy = "creator")
    var mapsUnderCreation: MutableSet<Map> = hashSetOf(),

    @ManyToMany(mappedBy = "redPlayers")
    var redGames: MutableSet<Game> = hashSetOf(),

    @ManyToMany(mappedBy = "bluePlayers")
    var blueGames: MutableSet<Game> = hashSetOf()
)
