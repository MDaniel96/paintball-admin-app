package demo.app.paintball.map.renderables.movables

import android.graphics.Canvas
import android.graphics.Rect
import android.os.SystemClock
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.map.renderables.MapElement
import demo.app.paintball.map.renderables.RenderableElement
import demo.app.paintball.map.renderables.PlayerElement

abstract class MovableElement(val name: String) : RenderableElement() {

    companion object {
        const val SIZE = 3
        const val MAX_TIME_BETWEEN_POSITION_UPDATES = 15_000

        fun create(user: User, team: Team) = when (team) {
            Team.RED -> RedPlayerElement(user.username)
            Team.BLUE -> BluePlayerElement(user.username)
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
            val distanceFromPlayerX = (PlayerElement.posX - posX) / MapElement.zoom
            val distanceFromPlayerY = (PlayerElement.posY - posY) / MapElement.zoom
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