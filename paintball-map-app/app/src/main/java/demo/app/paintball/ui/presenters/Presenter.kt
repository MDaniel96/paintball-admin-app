package demo.app.paintball.ui.presenters

abstract class Presenter<S> {

    protected var screen: S? = null

    open fun attachScreen(screen: S) {
        this.screen = screen
    }

    open fun detachScreen() {
        this.screen = null
    }
}