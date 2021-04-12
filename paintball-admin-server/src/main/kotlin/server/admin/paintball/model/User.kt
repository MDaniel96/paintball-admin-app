package server.admin.paintball.model

import com.fasterxml.jackson.annotation.JsonIgnore
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

    @JsonIgnore
    @ManyToMany(mappedBy = "redPlayers")
    var redGames: MutableSet<Game> = hashSetOf(),

    @JsonIgnore
    @ManyToMany(mappedBy = "bluePlayers")
    var blueGames: MutableSet<Game> = hashSetOf()
) {

    fun addMapUnderCreation(map: Map) {
        mapsUnderCreation.add(map)
        map.creator = this
    }
}
