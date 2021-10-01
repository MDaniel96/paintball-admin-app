package demo.app.paintball.config.map

import demo.app.paintball.R

class TestMapConfig : MapConfig() {

    // TODO
    override val imageDrawableId = R.drawable.map_gyenes

    override val imageWidthPx = 2_049

    override val mapOrientation = 46

    override val minZoom = 3.3
    override val maxZoom = 1.3

    override val anchorOriginPosX = 2_470
    override val anchorOriginPosY = 14_600

    override val anchorOriginPxX = 175

    override val anchors = listOf(
        intArrayOf(0, 0, 1100),
        intArrayOf(3_795, 0, 1100),
        intArrayOf(-80, 4_135, 1100),
        intArrayOf(3_795, 4_135, 1100),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )
}
