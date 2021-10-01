package demo.app.paintball.map.rendering

import android.graphics.Canvas
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.map.renderables.Anchor
import demo.app.paintball.map.renderables.Map
import demo.app.paintball.map.renderables.User
import demo.app.paintball.map.renderables.movables.Movable
import demo.app.paintball.util.clear

class Renderer(private val width: Int, private val height: Int) {

    private val movables = mutableListOf<Movable>()
    private val anchors = mutableListOf<Anchor>()

    private val map = Map()
    private val user = User()

    init {
        map.setSize(width, height)
        user.setSize(width, height)
    }

    fun draw(canvas: Canvas) {
        canvas.clear()
        map.render(canvas)
        anchors.forEach { it.render(canvas) }
        movables.forEach { it.render(canvas) }
        user.render(canvas)
    }

    fun setPlayerPosition(posX: Int, posY: Int) {
        User.posX = posX
        User.posY = posY
    }

    fun setPlayerOrientation(degree: Float) {
        user.setOrientation(degree)
    }

    fun setMovablePosition(movableName: String, posX: Int, posY: Int) {
        movables.find { it.name == movableName }
            ?.apply {
                this.posX = posX
                this.posY = posY
            }
    }

    fun addPlayer(newPlayer: Player) {
        val player = Movable.create(newPlayer)
        player.setSize(width, height)
        movables.add(player)
    }

    fun removePlayer(playerName: String) {
        movables.find { it.name == playerName }?.let {
            movables.remove(it)
        }
    }

    fun zoom(scaleFactor: Float) {
        map.scale(scaleFactor)
    }

    fun addAnchor(posX: Int, posY: Int) {
        val anchor = Anchor(posX, posY)
        anchor.setSize(width, height)
        anchors.add(anchor)
    }
}