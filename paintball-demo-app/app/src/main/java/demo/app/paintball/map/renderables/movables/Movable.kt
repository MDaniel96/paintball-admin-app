package demo.app.paintball.map.renderables.movables

import android.graphics.Canvas
import android.graphics.Rect
import android.os.SystemClock
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.map.renderables.Map
import demo.app.paintball.map.renderables.Renderable
import demo.app.paintball.map.renderables.User

abstract class Movable(val name: String) : Renderable() {

    companion object {
        const val SIZE = 3
        const val MAX_TIME_BETWEEN_POSITION_UPDATES = 1_500

        fun create(player: Player) = when (player.team) {
            Player.Team.RED -> RedPlayer(player.name)
            Player.Team.BLUE -> BluePlayer(player.name)
            else -> RedPlayer(player.name)
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
            val distanceFromPlayerX = (User.posX - posX) / Map.zoom
            val distanceFromPlayerY = (User.posY - posY) / Map.zoom
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