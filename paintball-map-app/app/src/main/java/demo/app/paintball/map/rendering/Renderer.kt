package demo.app.paintball.map.rendering

import android.graphics.Canvas
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Map
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.map.renderables.AnchorElement
import demo.app.paintball.map.renderables.MapElement
import demo.app.paintball.map.renderables.UserElement
import demo.app.paintball.map.renderables.movables.MovableElement
import demo.app.paintball.util.clear

class Renderer(private val width: Int, private val height: Int) {

    private val movables = mutableListOf<MovableElement>()
    private val anchors = mutableListOf<AnchorElement>()

    private var mapElement: MapElement? = null
    private var userElement: UserElement? = null

    fun initMap(map: Map) {
        mapElement = MapElement(map.id)
        userElement = UserElement(map.orientation)

        mapElement?.setSize(width, height)
        userElement?.setSize(width, height)
    }

    fun draw(canvas: Canvas) {
        canvas.clear()
        mapElement?.render(canvas)
        anchors.forEach { it.render(canvas) }
        movables.forEach { it.render(canvas) }
        userElement?.render(canvas)
    }

    fun setPlayerPosition(posX: Int, posY: Int) {
        UserElement.posX = posX
        UserElement.posY = posY
    }

    fun setPlayerOrientation(degree: Float) {
        userElement?.setOrientation(degree)
    }

    fun setMovablePosition(movableName: String, posX: Int, posY: Int) {
        movables.find { it.name == movableName }
            ?.apply {
                this.posX = posX
                this.posY = posY
            }
    }

    fun addUser(newUser: User, team: Team) {
        val user = MovableElement.create(newUser, team)
        user.setSize(width, height)
        movables.add(user)
    }

    fun removePlayer(playerName: String) {
        movables.find { it.name == playerName }?.let {
            movables.remove(it)
        }
    }

    fun zoom(scaleFactor: Float) {
        mapElement?.scale(scaleFactor)
    }

    fun addAnchor(posX: Int, posY: Int) {
        val anchor = AnchorElement(posX, posY)
        anchor.setSize(width, height)
        anchors.add(anchor)
    }
}