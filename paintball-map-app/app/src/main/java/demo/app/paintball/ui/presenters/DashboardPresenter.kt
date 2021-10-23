package demo.app.paintball.ui.presenters

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.ui.activities.JoinGameActivity
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.ui.screens.DashboardScreen
import demo.app.paintball.util.ErrorHandler
import kotlinx.android.synthetic.main.fragment_join_game.*
import javax.inject.Inject

class DashboardPresenter @Inject constructor() : Presenter<DashboardScreen>(), RestService.SuccessListener {

    companion object {
        val permissionsNeeded = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    var createdGames = emptyList<Game>()
    var users = emptyList<User>()

    @Inject
    lateinit var restService: RestService

    override fun attachScreen(screen: DashboardScreen) {
        super.attachScreen(screen)
        injector.inject(this)
        restService.apply { listener = this@DashboardPresenter; errorListener = ErrorHandler }

        checkPermissions(permissionsNeeded)
    }

    fun queryJoinGameData() {
        restService.getCreatedGames()
        restService.getUsers()
    }

    fun joinGame(selectedGameId: Long, selectedUser: User) {
        PaintballApplication.currentUser = selectedUser
        val intent = Intent(screen as Activity, JoinGameActivity::class.java)
        intent.putExtra("SELECTED_GAME_ID", selectedGameId)
        (screen as Activity).startActivity(intent)
    }

    override fun onGetGame(game: Game) {
    }

    override fun onGetCreatedGames(games: List<Game>) {
        createdGames = games
        screen?.showCreatedGames()
    }

    override fun onGetUsers(users: List<User>) {
        this.users = users
    }

    override fun onAddUserToTeam(team: Team) {
    }

    private fun checkPermissions(permissions: List<String>) {
        permissions.filter { checkSelfPermission(context, it) != PERMISSION_GRANTED }
            .map { ActivityCompat.requestPermissions(screen as Activity, permissions.toTypedArray(), 0) }
    }
}