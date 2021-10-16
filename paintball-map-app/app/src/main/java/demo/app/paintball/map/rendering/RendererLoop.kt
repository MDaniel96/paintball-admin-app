package demo.app.paintball.map.rendering

import android.graphics.Canvas
import demo.app.paintball.data.rest.models.Map
import demo.app.paintball.data.rest.models.Player

class RenderLoop(private val view: MapViewImpl, width: Int, height: Int) : Thread() {

    companion object {
        private const val FPS: Long = 30
        private const val TIME_BETWEEN_FRAMES = 1000 / FPS
    }

    private val renderer = Renderer(width, height)

    var running = false

    private fun getTime() = System.currentTimeMillis()

    override fun run() {
        while (running) {
            val renderStart = getTime()
            draw()
            val renderEnd = getTime()

            val sleepTime = TIME_BETWEEN_FRAMES - (renderEnd - renderStart)
            if (sleepTime > 0) {
                sleep(sleepTime)
            } else {
                sleep(5)
            }
        }
    }

    private fun draw() {
        var canvas: Canvas? = null
        try {
            canvas = view.holder.lockCanvas()
            synchronized(view.holder) {
                renderer.draw(canvas)
            }
        } finally {
            if (canvas != null) {
                view.holder.unlockCanvasAndPost(canvas)
            }
        }
    }

    fun setMap(map: Map) {
        renderer.setMap(map)
    }

    fun setPlayerPosition(posX: Int, posY: Int) {
        renderer.setPlayerPosition(posX, posY)
    }

    fun setPlayerOrientation(degree: Float) {
        renderer.setPlayerOrientation(degree)
    }

    fun setMovablePosition(playerName: String, posX: Int, posY: Int) {
        renderer.setMovablePosition(playerName, posX, posY)
    }

    fun addPlayer(player: Player) {
        renderer.addPlayer(player)
    }

    fun removePlayer(playerName: String) {
        renderer.removePlayer(playerName)
    }

    fun zoom(scaleFactor: Float) {
        renderer.zoom(scaleFactor)
    }

    fun addAnchor(posX: Int, posY: Int) {
        renderer.addAnchor(posX, posY)
    }
}