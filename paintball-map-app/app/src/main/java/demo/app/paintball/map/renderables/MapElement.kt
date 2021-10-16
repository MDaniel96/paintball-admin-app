package demo.app.paintball.map.renderables

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.data.rest.GameApi

class MapElement(mapId: Long) : RenderableElement() {

    companion object {
        const val MIN_ZOOM = 1.5 // TODO
        const val MAX_ZOOM = 0.8 // TODO
        const val MAX_SCALE_FACTOR = 2

        var zoom = MIN_ZOOM
    }

    init {
        loadImageFromUrl(mapId)
    }

    override var image: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ALPHA_8)

    private var bitmapDrawable: BitmapDrawable? = null

    override fun render(canvas: Canvas) {
        if (bitmapDrawable != null) {
            val translateX = (screenWidth / 2 - UserElement.posX / zoom).toInt()
            val translateY = (screenHeight / 2 - UserElement.posY / zoom).toInt()

            val src = Rect(0, 0, image.width, image.height)
            val dst = Rect(
                translateX,
                translateY,
                translateX + (image.width / zoom).toInt(),
                translateY + (image.height / zoom).toInt()
            )
            canvas.drawBitmap(bitmapDrawable!!.bitmap, src, dst, null)
        }
    }

    // rescaling(2): https://en.wikipedia.org/wiki/Feature_scaling
    fun scale(scaleFactor: Float) {
        zoom = MIN_ZOOM + (scaleFactor - 1) * (MAX_ZOOM - MIN_ZOOM) / (MAX_SCALE_FACTOR - 1)
    }

    private fun loadImageFromUrl(mapId: Long) {
        Glide.with(context)
            .asBitmap()
            .load("${GameApi.BASE_URL}paintball-admin/api/map/image/$mapId")
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    image = resource
                    bitmapDrawable = BitmapDrawable(context.resources, image).apply {
                        tileModeX = Shader.TileMode.MIRROR
                        tileModeY = Shader.TileMode.MIRROR
                    }

                }
                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }
}