package server.admin.paintball.model

import javax.persistence.*

@Entity
class Obstacle(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    var x: Long = -1L,

    @Column(nullable = false)
    var y: Long = -1L,

    @Column(nullable = false)
    var width: Long = -1L,

    @Column(nullable = false)
    var height: Long = -1L,

    @ManyToOne
    var map: Map? = null
)
