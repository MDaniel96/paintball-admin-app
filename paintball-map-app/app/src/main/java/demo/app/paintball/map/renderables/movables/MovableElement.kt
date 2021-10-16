package demo.app.paintball.map.renderables.movables

import android.graphics.Canvas
import android.graphics.Rect
import android.os.SystemClock
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.map.renderables.MapElement
import demo.app.paintball.map.renderables.RenderableElement
import demo.app.paintball.map.renderables.UserElement

abstract class MovableElement(val name: String) : RenderableElement() {

    companion object {
        const val SIZE = 3
        const val MAX_TIME_BETWEEN_POSITION_UPDATES = 1_500

        fun create(player: Player) = when (player.team) {
            Player.Team.RED -> RedPlayerElement(player.name)
            Player.Team.BLUE -> BluePlayerElement(player.name)
            else -> RedPlayerElement(player.name)
        }
    }

    var posX: Int = 0
    var posY: Int = 0
        set(value) {
            field = value
            lastUpdate = SystemClock.uptimeMillis()
        }

    private var lastUpdate: Long = SystemClock.uptimeMillis()

    override fun render(canvas: Canvas) {
        if (isVisible()) {
            val distanceFromPlayerX = (UserElement.posX - posX) / MapElement.zoom
            val distanceFromPlayerY = (UserElement.posY - posY) / MapElement.zoom
            val translateX = (screenWidth / 2 - distanceFromPlayerX).toInt() - (image.width / SIZE / 2)
            val translateY = (screenHeight / 2 - distanceFromPlayerY).toInt() - (image.height / SIZE / 2)

            val src = Rect(0, 0, image.width, image.height)
            val dst = Rect(
                translateX,
                translateY,
                translateX + image.width / SIZE,
                translateY + image.height / SIZE
            )
            canvas.drawBitmap(image, src, dst, null)
        }
    }

    private fun isVisible() =
        (SystemClock.uptimeMillis() - lastUpdate) < MAX_TIME_BETWEEN_POSITION_UPDATES
}