package demo.app.paintball.ui.fragments.buttons

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import demo.app.paintball.R

abstract class MapButtonsFragment : Fragment() {

    abstract var rootLayout: View

    abstract var btnBottom: View
    abstract var btnBottomLayout: View
    abstract var btnBottomTextView: View

    abstract var btnMiddle: View
    abstract var btnMiddleLayout: View
    abstract var btnMiddleTextView: View

    protected var screenHeight = 0F
    protected var translateY = 0F
    protected var isOpen = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenHeight = resources.displayMetrics.heightPixels.toFloat()
    }

    fun initLevel(level: Int) {
        translateY = level * screenHeight
    }

    fun changeLevel(level: Int) {
        if (isOpen) {
            translateY = level * screenHeight
            rootLayout.animate().translationY(translateY)
            rootLayout.animate().translationY(translateY)
        }
    }

    open fun show() {
        isOpen = true
        rootLayout.animate().translationY(translateY)

        btnBottomLayout.animate()
            .translationY(-resources.getDimension(R.dimen.fab_bottom_button_translate))
        btnBottom.animate().rotation(0F)
        btnBottomTextView.animate().alpha(1F).duration = 600

        btnMiddleLayout.animate()
            .translationY(-resources.getDimension(R.dimen.fab_middle_button_translate))
        btnMiddle.animate().rotation(0F)
        btnMiddleTextView.animate().alpha(1F).duration = 600
    }

    open fun hide() {
        isOpen = false
        rootLayout.animate().translationY(0F)

        btnBottomLayout.animate().translationY(0F)
        btnBottom.animate().rotation(-120F)
        btnBottomTextView.animate().alpha(0F).duration = 300

        btnMiddleLayout.animate().translationY(0F)
        btnMiddle.animate().rotation(-120F)
        btnMiddleTextView.animate().alpha(0F).duration = 300
    }
}