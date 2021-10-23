package demo.app.paintball.positioning.calculators.gps

import android.location.Location
import demo.app.paintball.positioning.calculators.PositionCalculatorListener

interface GpsPositionCalculator {

    var listener: PositionCalculatorListener

    fun calculate(location: Location)
}