package demo.app.paintball.map

import android.view.View
import demo.app.paintball.data.rest.models.Map
import demo.app.paintball.data.rest.models.Player

interface MapView {

    fun setMap(map: Map)

    fun setPlayerPosition(posX: Int, posY: Int)

    fun setPlayerOrientation(degree: Float)

    fun setMovablePosition(playerName: String, posX: Int, posY: Int)

    fun addPlayer(player: Player)

    fun removePlayer(playerName: String)

    fun zoom(scaleFactor: Float)

    fun addAnchor(posX: Int, posY: Int)

    fun setOnTouchListener(listener: View.OnTouchListener)
}