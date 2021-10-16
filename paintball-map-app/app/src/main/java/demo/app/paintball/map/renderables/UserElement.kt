package demo.app.paintball.map.renderables

import android.graphics.*
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.R
import demo.app.paintball.data.rest.models.Player

class UserElement(private val mapOrientation: Int) : RenderableElement() {

    companion object {
        const val SIZE = 3
        const val PULSING_SIZE = 1.34
        const val PULSING_SPEED = 2

        const val SPRITE_VERTICAL = 8
        const val SPRITE_HORIZONTAL = 4
        const val SPRITE_UNUSED = 7

        const val PHONE_ORIENTATION = 90.0F  // east

        var posX = 1300 // TODO 1800.xToMapPx()
        var posY = 1400 // TODO 1500.yToMapPx()
    }

    private var screenCenterX = 0F
    private var screenCenterY = 0F

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_arrow_white)

    private val sprite: Bitmap = BitmapFactory.decodeResource(
        context.resources, when (player.team) {
            Player.Team.RED -> R.drawable.sprite_pulse_red
            Player.Team.BLUE -> R.drawable.sprite_pulse_blue
            else -> R.drawable.sprite_pulse_gr
        }
    )

    private var loopCounter = 0
    private var slowCounter = 0
    private var spriteState = 0

    private val imageMatrix = Matrix()

    override fun setSize(x: Int, y: Int) {
        super.setSize(x, y)

        screenCenterX = (x / 2).toFloat()
        screenCenterY = (y / 2).toFloat()

        setOrientation(0F)
    }

    override fun render(canvas: Canvas) {
        drawSprite(canvas)
        canvas.drawBitmap(image, imageMatrix, null)
    }

    private fun drawSprite(canvas: Canvas) {
        val spriteWidth = sprite.width / SPRITE_HORIZONTAL
        val spriteHeight = sprite.height / SPRITE_VERTICAL

        val translateX = (screenCenterX.toInt() - (spriteWidth / PULSING_SIZE / 2)).toInt()
        val translateY = (screenCenterY.toInt() - (spriteHeight / PULSING_SIZE / 2)).toInt()

        spriteState = if (loopCounter++ % PULSING_SPEED == 0)
            slowCounter++ % (SPRITE_HORIZONTAL * SPRITE_VERTICAL - SPRITE_UNUSED) else
            spriteState

        val x = spriteWidth * (spriteState % SPRITE_HORIZONTAL)
        val y = spriteHeight * (spriteState / SPRITE_HORIZONTAL)
        val src = Rect(x, y, x + spriteWidth, y + spriteHeight)
        val dst = Rect(
            translateX,
            translateY,
            translateX + (spriteWidth / PULSING_SIZE).toInt(),
            translateY + (spriteHeight / PULSING_SIZE).toInt()
        )
        canvas.drawBitmap(sprite, src, dst, null)
    }

    fun setOrientation(degree: Float) {
        val translateX = screenCenterX - (image.width / SIZE / 2)
        val translateY = screenCenterY - (image.height / SIZE / 2)

        val src = RectF(0F, 0F, image.width.toFloat(), image.height.toFloat())
        val dst = RectF(
            translateX,
            translateY,
            translateX + image.width / SIZE,
            translateY + image.height / SIZE
        )
        imageMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER)

        val mapOrientation = mapOrientation.toFloat()
        val phoneDegree = (degree + PHONE_ORIENTATION) % 360.0F
        val mapDegree = (phoneDegree - mapOrientation) % 360.0F
        imageMatrix.postRotate(
            mapDegree,
            translateX + (image.width / 2) / SIZE,
            translateY + (image.height / 2) / SIZE
        )
    }
}