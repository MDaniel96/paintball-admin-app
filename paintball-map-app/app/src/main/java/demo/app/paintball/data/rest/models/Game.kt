package demo.app.paintball.data.rest.models

class Game(
    val id: Long = -1L,
    var name: String = "",
    var type: String = "",
    var state: State = State.CREATED,
    val localizationMode: LocalizationMode = LocalizationMode.GPS,
    var redPlayers: MutableSet<User> = hashSetOf(),
    var bluePlayers: MutableSet<User> = hashSetOf(),
    var deadPlayers: MutableSet<User> = hashSetOf(),
    var map: Map? = null
) {

    val playerCount: Int
        get() = redPlayers.size + bluePlayers.size

    enum class State(val value: String) {
        CREATED("CREATED"),
        STARTED("STARTED"),
        FINISHED("FINISHED");
    }

    enum class Team(val value: String) {
        BLUE("BLUE"),
        RED("RED");
    }

    enum class LocalizationMode(val value: String) {
        GPS("GPS"),
        UWB("UWB")
    }
}