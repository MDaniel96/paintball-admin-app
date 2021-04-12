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
    var imageUrl: String = "",

    @Column(nullable = false)
    var orientation: Int = 0,

    @ManyToOne
    var location: Location? = null,

    @OneToMany(mappedBy = "map")
    var games: MutableSet<Game> = hashSetOf(),

    @ManyToOne
    var creator: User? = null
) {

    fun addGame(game: Game) {
        games.add(game)
        game.map = this
    }
}
