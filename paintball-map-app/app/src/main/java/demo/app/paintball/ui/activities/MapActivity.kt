package demo.app.paintball.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import demo.app.paintball.PaintballApplication.Companion.currentUser
import demo.app.paintball.PaintballApplication.Companion.injector
import demo.app.paintball.R
import demo.app.paintball.data.rest.enums.Team
import demo.app.paintball.data.rest.models.Anchor
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.User
import demo.app.paintball.map.MapView
import demo.app.paintball.map.sensors.GestureSensor
import demo.app.paintball.map.sensors.Gyroscope
import demo.app.paintball.positioning.converters.PositionConverter
import demo.app.paintball.ui.fragments.buttons.MapButtonsFragment
import demo.app.paintball.ui.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.ui.fragments.panels.MapStatsPanelFragment
import demo.app.paintball.ui.presenters.MapPresenter
import demo.app.paintball.ui.screens.MapScreen
import demo.app.paintball.util.setBackgroundTint
import demo.app.paintball.util.setSrc
import demo.app.paintball.util.toDegree
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.activity_map.*
import javax.inject.Inject

class MapActivity : AppCompatActivity(), MapScreen,
    GestureSensor.GestureListener, Gyroscope.GyroscopeListener, ConnectTagFragment.ConnectTagListener {

    @Inject
    lateinit var mapPresenter: MapPresenter

    private var isMapButtonsOpen = false

    private lateinit var mapViewElement: MapView
    private lateinit var mainButtons: MapButtonsFragment
    private lateinit var chatButtons: MapButtonsFragment

    private lateinit var statsPanel: MapStatsPanelFragment

    private lateinit var gyroscope: Gyroscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        injector.inject(this)
        mapPresenter.attachScreen(this)

        mapViewElement = mapView
        mainButtons = supportFragmentManager.findFragmentById(R.id.mainButtonsFragment) as MapButtonsFragment
        chatButtons = supportFragmentManager.findFragmentById(R.id.chatButtonsFragment) as MapButtonsFragment

        statsPanel = supportFragmentManager.findFragmentById(R.id.statsPanelFragment) as MapStatsPanelFragment

        mainButtons.initLevel(0)
        chatButtons.initLevel(1)

        mapViewElement.setOnTouchListener(GestureSensor(gestureListener = this, scrollPanel = buttonsPanel))
        gyroscope = Gyroscope(gyroscopeListener = this)

        val selectedGameId = intent.getLongExtra("SELECTED_GAME_ID", -1L)
        mapPresenter.queryGame(selectedGameId)

        fabActivateButtons.setOnClickListener {
            if (isMapButtonsOpen) hideButtons() else showButtons()
        }
    }

    override fun initMap(game: Game) {
        mapViewElement.initMap(game.map!!)
        statsPanel.refresh(game)
    }

    override fun showUsers(bluePlayers: Set<User>, redPlayers: Set<User>) {
        redPlayers.filter { it.id != currentUser.id }
            .forEach { mapViewElement.addUser(it, Team.RED) }
        bluePlayers.filter { it.id != currentUser.id }
            .forEach { mapViewElement.addUser(it, Team.BLUE) }
    }

    override fun showAnchors(anchors: Set<Anchor>, positionConverter: PositionConverter) {
        if (resources.getBoolean(R.bool.displayAnchors)) {
            anchors.forEach {
                mapViewElement.addAnchor(
                    positionConverter.mmToPx(it.x.toInt()),
                    positionConverter.mmToPx(it.y.toInt())
                )
            }
        }
    }

    override fun removePlayer(playerName: String, game: Game) {
        statsPanel.refresh(game)
        mapViewElement.removeUser(playerName)
        toast(getString(R.string.player_left_the_game, playerName))
    }

    override fun setPlayerPosition(posX: Int, posY: Int) {
        mapViewElement.setPlayerPosition(posX, posY)
    }

    override fun setMovablePosition(playerName: String, posX: Int, posY: Int) {
        mapViewElement.setMovablePosition(playerName, posX, posY)
    }

    override fun onResume() {
        super.onResume()
        gyroscope.start()
    }

    override fun onPause() {
        super.onPause()
        gyroscope.stop()
    }

    override fun onBackPressed() {
    }

    override fun onScaleChanged(scaleFactor: Float) {
        mapViewElement.zoom(scaleFactor)
    }

    override fun onZoomIn() {
        hideButtons()
    }

    override fun onZoomOut() {
        statsPanel.show()
    }

    override fun onScrollUp() {
        mainButtons.changeLevel(-1)
        chatButtons.changeLevel(0)
        btnPagingChat.setBackgroundTint(R.color.transparentWhite)
        btnPagingMain.setBackgroundTint(R.color.lightTransparentGray)
    }

    override fun onScrollDown() {
        mainButtons.changeLevel(0)
        chatButtons.changeLevel(1)
        btnPagingChat.setBackgroundTint(R.color.lightTransparentGray)
        btnPagingMain.setBackgroundTint(R.color.transparentWhite)
    }

    override fun onOrientationChanged(radian: Float) {
        mapViewElement.setPlayerOrientation(radian.toDegree())
    }

    override fun onTagConnected() {
        mapPresenter.bleService.startPositionSending()
    }

    private fun showButtons() {
        isMapButtonsOpen = true
        fabActivateButtons.setSrc(R.drawable.ic_unfold_less)
        fabActivateButtons.animate().rotation(180F)
        buttonsPagingLayout.animate().translationX(-50F)

        statsPanel.show()
        mainButtons.show()
        chatButtons.show()
    }

    private fun hideButtons() {
        isMapButtonsOpen = false
        fabActivateButtons.setSrc(R.drawable.ic_unfold_more)
        fabActivateButtons.animate().rotation(-180F)
        buttonsPagingLayout.animate().translationX(0F)

        statsPanel.hide()
        mainButtons.hide()
        chatButtons.hide()
    }

    override fun onDestroy() {
        mapPresenter.detachScreen()
        super.onDestroy()
    }
}