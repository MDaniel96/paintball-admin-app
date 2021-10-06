package demo.app.paintball.data.rest.models

class OldGame(
    var name: String = "",
    var type: String = "",
    var time: Int = 0,
    var admin: String = "",
    var redTeam: MutableList<Player> = mutableListOf(),
    var blueTeam: MutableList<Player> = mutableListOf(),
    val playerCnt: Int = 0
) {

    fun leave(playerName: String) {
        redTeam.leave(playerName)
        blueTeam.leave(playerName)
    }
}

fun MutableList<Player>.leave(name: String) {
    this.filter { it.name == name }.forEach { it.hasLeft = true }
}

fun MutableList<Player>.getRemainingPlayers() = this.filter { !it.hasLeft }.count()