package demo.app.paintball.util

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaPlayer
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import demo.app.paintball.PaintballApplication.Companion.context
import java.io.File

// ====================================
//  CONVENIENCE
// ====================================

fun toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, text, duration).show()
}

fun Canvas.clear() {
    this.drawColor(Color.DKGRAY)
}

@SuppressLint("NewApi")
fun View.setBackgroundTint(colorId: Int) {
    this.backgroundTintList = ContextCompat.getColorStateList(context, colorId)
}

fun FloatingActionButton.setSrc(imageId: Int) {
    this.setImageDrawable(ResourcesCompat.getDrawable(resources, imageId, null))
}

fun View.fadeIn(delay: Long = 1000, duration: Long = 800) {
    AlphaAnimation(0.0f, 1.0f).apply {
        this.startOffset = delay
        this.duration = duration
    }.let {
        this.startAnimation(it)
    }
}

// ====================================
//  MATH
// ====================================

fun Float.toDegree() = Math.toDegrees(this.toDouble()).toFloat()

fun Float.to2PIRadiant() = if (this < 0) (2 * Math.PI + this).toFloat() else this

// ====================================
//  AUDIO
// ====================================

fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

fun String.fromHexToByteArray() = this.chunked(2).map { it.toInt(16).toByte() }.toByteArray()

fun ByteArray.playAudio() {
    val filePath = context.cacheDir.toString() + "/teamChat.3gp"
    File(filePath).run {
        writeBytes(this@playAudio)
    }
    MediaPlayer().run {
        setDataSource(filePath)
        prepare()
        start()
        setOnCompletionListener {
            it.reset()
            it.release()
        }
    }
}