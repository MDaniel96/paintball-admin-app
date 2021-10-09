package demo.app.paintball

import android.app.Application
import android.content.Context
import demo.app.paintball.data.DaggerDataServiceInjector
import demo.app.paintball.data.DataServiceInjector
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User

class PaintballApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var services: DataServiceInjector
        lateinit var player: Player
        lateinit var user: User
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        services = DaggerDataServiceInjector.builder().build()
        player = Player()
        user = User()
    }
}