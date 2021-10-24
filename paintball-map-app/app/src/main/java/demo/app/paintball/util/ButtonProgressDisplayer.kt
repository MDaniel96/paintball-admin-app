package demo.app.paintball.util

import android.app.Activity
import android.os.Handler
import android.os.Looper
import mbanje.kurt.fabbutton.FabButton

class ButtonProgressDisplayer(
    private val button: FabButton,
    private val activity: Activity
) {

    companion object {
        const val MAX_PROGRESS_VALUE = 100F
        const val DELAY = 400L
    }

    private var progressValue = 0F
    private var unitOfProgressChange = 0F

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            progressValue += unitOfProgressChange
            activity.runOnUiThread {
                button.setProgress(progressValue)
                if (progressValue <= MAX_PROGRESS_VALUE) {
                    handler.postDelayed(this, DELAY)
                }
            }
        }
    }

    fun show(duration: Long) {
        progressValue = 0F
        unitOfProgressChange = (DELAY / duration.toFloat()) * MAX_PROGRESS_VALUE
        button.showProgress(true)
        runnable.run()
    }

    fun stop() {
        handler.removeCallbacks(runnable)
        activity.runOnUiThread {
            button.setProgress(MAX_PROGRESS_VALUE)
        }
    }
}