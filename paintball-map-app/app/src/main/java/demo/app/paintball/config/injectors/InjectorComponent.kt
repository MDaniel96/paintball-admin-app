package demo.app.paintball.config.injectors

import dagger.Component
import demo.app.paintball.activities.DashboardActivity
import demo.app.paintball.activities.JoinGameActivity
import demo.app.paintball.activities.MapActivity
import demo.app.paintball.fragments.buttons.ChatButtonsFragmentImpl
import demo.app.paintball.fragments.buttons.MainButtonsFragmentImpl
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.dialogs.JoinGameFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [InjectorModule::class])
interface InjectorComponent {

    fun inject(dashboardActivity: DashboardActivity)
    fun inject(joinGameActivity: JoinGameActivity)
    fun inject(mapActivity: MapActivity)

    fun inject(connectTagFragment: ConnectTagFragment)
    fun inject(joinGameFragment: JoinGameFragment)
    fun inject(mainButtonsFragmentImpl: MainButtonsFragmentImpl)
    fun inject(chatButtonsFragmentImpl: ChatButtonsFragmentImpl)
}