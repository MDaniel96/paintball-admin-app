package server.admin.paintball.model

import javax.persistence.*

@Entity
class Map(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var orientation: Int = 0,

    @Column(nullable = false)
    var width: Long = -1L,

    @Column(nullable = false)
    var height: Long = -1L,

    @Column(nullable = false)
    var borderX: Long = -1L,

    @Column(nullable = false)
    var borderY: Long = -1L,

    @Column(nullable = false)
    var borderWidth: Long = -1L,

    @Column(nullable = false)
    var borderHeight: Long = -1L,

    @Column(nullable = false)
    var anchorRadiusInMm: Long = -1L,

    @Column(nullable = false)
    var anchorRadiusInPixels: Long = -1L,

    @ManyToOne
    var location: Location? = null,

    @OneToMany(mappedBy = "map")
    var obstacles: MutableSet<Obstacle> = hashSetOf(),

    @OneToMany(mappedBy = "map")
    var anchors: MutableSet<Anchor> = hashSetOf(),

    @OneToMany(mappedBy = "map")
    var games: MutableSet<Game> = hashSetOf(),

    @ManyToOne
    var creator: User? = null
)
