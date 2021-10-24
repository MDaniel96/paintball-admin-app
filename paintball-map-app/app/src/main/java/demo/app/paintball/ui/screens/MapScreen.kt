package demo.app.paintball.ui.screens

import demo.app.paintball.data.rest.models.Anchor
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.positioning.converters.PositionConverter

interface MapScreen {

    fun initMap(game: Game)

    fun showUsers(bluePlayers: Set<User>, redPlayers: Set<User>)

    fun showAnchors(anchors: Set<Anchor>, positionConverter: PositionConverter)

    fun removePlayer(playerName: String, game: Game)

    fun setPlayerPosition(posX: Int, posY: Int)

    fun setMovablePosition(playerName: String, posX: Int, posY: Int)
}