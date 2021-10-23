package demo.app.paintball

import android.app.Application
import android.content.Context
import demo.app.paintball.config.injectors.DaggerInjectorComponent
import demo.app.paintball.config.injectors.InjectorComponent
import demo.app.paintball.config.injectors.InjectorModule
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.User

class PaintballApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var injector: InjectorComponent
        lateinit var currentUser: User
        lateinit var currentTeam: Team
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        injector = DaggerInjectorComponent.builder().injectorModule(InjectorModule()).build()
        currentUser = User()
        currentTeam = Team.BLUE
    }
}