package demo.app.paintball.map.renderables.movables

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R

class RedPlayerElement(name: String) : MovableElement(name) {

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_red_player)
}