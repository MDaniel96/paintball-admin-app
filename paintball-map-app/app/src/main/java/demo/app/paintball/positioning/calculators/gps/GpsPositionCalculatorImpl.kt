package demo.app.paintball.positioning.calculators.gps

import android.location.Location
import demo.app.paintball.positioning.calculators.PositionCalculatorListener

class GpsPositionCalculatorImpl(private val originLocation: Location) : GpsPositionCalculator {

    override lateinit var listener: PositionCalculatorListener

    override fun calculate(location: Location) {
        val xLocation = Location("").apply {
            latitude = originLocation.latitude
            longitude = location.longitude
        }
        val yLocation = Location("").apply {
            latitude = location.latitude
            longitude = originLocation.longitude
        }

        val posX = (originLocation.distanceTo(xLocation) * 1000).toInt()
        val posY = (originLocation.distanceTo(yLocation) * 1000).toInt()
        listener.onPositionCalculated(posX, posY)
    }
}