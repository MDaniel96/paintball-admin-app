package demo.app.paintball

import android.app.Application
import android.content.Context
import demo.app.paintball.data.DaggerDataServiceInjector
import demo.app.paintball.data.DataServiceInjector
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.data.rest.models.User

class PaintballApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var services: DataServiceInjector
        lateinit var player: Player

        lateinit var currentUser: User
        lateinit var currentTeam: Team
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        services = DaggerDataServiceInjector.builder().build()
        player = Player()

        currentUser = User()
        currentTeam = Team.BLUE
    }
}