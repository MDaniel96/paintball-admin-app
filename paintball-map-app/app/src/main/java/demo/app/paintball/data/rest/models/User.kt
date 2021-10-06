package demo.app.paintball.data.rest.models

class User(
    val id: Long = -1L,
    var username: String = "",
    var mapsUnderCreation: MutableSet<Map> = hashSetOf(),
    var roles: MutableSet<Role> = hashSetOf()
)
