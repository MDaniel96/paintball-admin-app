package demo.app.paintball.positioning.gps

import android.location.Location
import demo.app.paintball.positioning.PositionCalculatorListener

interface GpsPositionCalculator {

    var listener: PositionCalculatorListener

    fun calculate(location: Location)
}