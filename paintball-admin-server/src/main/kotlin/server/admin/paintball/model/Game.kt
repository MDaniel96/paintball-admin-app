package server.admin.paintball.model

import java.time.LocalDate
import javax.persistence.*

@Entity
class Game(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var type: String = "",

    @Column(nullable = false)
    var state: State = State.CREATED,

    @Column(nullable = false)
    var date: LocalDate = LocalDate.now(),

    @ManyToMany
    var redPlayers: MutableSet<User> = hashSetOf(),

    @ManyToMany
    var bluePlayers: MutableSet<User> = hashSetOf(),

    @ManyToOne
    var map: Map? = null
) {

    enum class State(val value: String) {
        CREATED("CREATED"),
        STARTED("STARTED"),
        FINISHED("FINISHED");
    }
}
