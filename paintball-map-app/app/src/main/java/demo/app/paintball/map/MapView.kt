package demo.app.paintball.map

import android.view.View
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Map
import demo.app.paintball.data.rest.models.User

interface MapView {

    fun initMap(map: Map)

    fun setPlayerPosition(posX: Int, posY: Int)

    fun setPlayerOrientation(degree: Float)

    fun setMovablePosition(playerName: String, posX: Int, posY: Int)

    fun addUser(user: User, team: Team)

    fun removePlayer(playerName: String)

    fun zoom(scaleFactor: Float)

    fun addAnchor(posX: Int, posY: Int)

    fun setOnTouchListener(listener: View.OnTouchListener)
}