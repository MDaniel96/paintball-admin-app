package demo.app.paintball.map.rendering

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Map
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.map.MapView

class MapViewImpl : SurfaceView, MapView {

    private var renderLoop: RenderLoop? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // empty
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                var retry = true
                renderLoop?.running = false
                while (retry) {
                    try {
                        renderLoop?.join()
                        retry = false
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                val loop = RenderLoop(this@MapViewImpl, width, height)
                loop.running = true
                loop.start()
                renderLoop = loop
            }
        })
    }

    override fun initMap(map: Map) {
        renderLoop?.initMap(map)
    }

    override fun setPlayerPosition(posX: Int, posY: Int) {
        renderLoop?.setPlayerPosition(posX, posY)
    }

    override fun setMovablePosition(playerName: String, posX: Int, posY: Int) {
        renderLoop?.setMovablePosition(playerName, posX, posY)
    }

    override fun setPlayerOrientation(degree: Float) {
        renderLoop?.setPlayerOrientation(degree)
    }

    override fun addUser(user: User, team: Team) {
        renderLoop?.addUser(user, team)
    }

    override fun removeUser(playerName: String) {
        renderLoop?.removeUser(playerName)
    }

    override fun zoom(scaleFactor: Float) {
        renderLoop?.zoom(scaleFactor)
    }

    override fun addAnchor(posX: Int, posY: Int) {
        renderLoop?.addAnchor(posX, posY)
    }
}