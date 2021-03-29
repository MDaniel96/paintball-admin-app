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

    @JsonIgnore
    @ManyToMany
    var games: MutableSet<Game> = hashSetOf()
)
