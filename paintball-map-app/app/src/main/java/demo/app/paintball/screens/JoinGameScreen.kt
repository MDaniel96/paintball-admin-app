package demo.app.paintball.screens

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User

interface JoinGameScreen {

    fun showGameDetails(game: Game)

    fun showTeamLists(bluePlayers: MutableSet<User>, redPlayers: MutableSet<User>)

    fun displayAddRedPlayer()

    fun displayAddBluePlayer()

    fun stopTimer()
}