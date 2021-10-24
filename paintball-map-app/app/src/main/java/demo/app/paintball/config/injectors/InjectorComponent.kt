package demo.app.paintball.config.injectors

import dagger.Component
import demo.app.paintball.ui.activities.DashboardActivity
import demo.app.paintball.ui.activities.JoinGameActivity
import demo.app.paintball.ui.activities.MapActivity
import demo.app.paintball.ui.fragments.buttons.ChatButtonsFragmentImpl
import demo.app.paintball.ui.fragments.buttons.MainButtonsFragmentImpl
import demo.app.paintball.ui.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.ui.fragments.dialogs.JoinGameFragment
import demo.app.paintball.ui.presenters.DashboardPresenter
import demo.app.paintball.ui.presenters.JoinGamePresenter
import demo.app.paintball.ui.presenters.MapPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [InjectorModule::class])
interface InjectorComponent {

    fun inject(dashboardActivity: DashboardActivity)
    fun inject(joinGameActivity: JoinGameActivity)
    fun inject(mapActivity: MapActivity)

    fun inject(dashboardPresenter: DashboardPresenter)
    fun inject(joinGamePresenter: JoinGamePresenter)
    fun inject(mapPresenter: MapPresenter)

    fun inject(connectTagFragment: ConnectTagFragment)
    fun inject(joinGameFragment: JoinGameFragment)
    fun inject(mainButtonsFragmentImpl: MainButtonsFragmentImpl)
    fun inject(chatButtonsFragmentImpl: ChatButtonsFragmentImpl)
}