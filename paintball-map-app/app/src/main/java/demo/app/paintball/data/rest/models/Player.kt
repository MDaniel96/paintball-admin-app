package demo.app.paintball.data.rest.models

class Player(
    var name: String = "",
    var deviceName: String = "",
    var team: Team? = null,
    var isAdmin: Boolean = false,
    var hasLeft: Boolean = false
) {

    enum class Team(val value: String) {
        RED("RED"),
        BLUE("BLUE");
    }
}