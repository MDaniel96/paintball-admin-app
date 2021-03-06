package server.admin.paintball.model

import javax.persistence.*

@Entity
class Location(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    var name: String = "",
)
