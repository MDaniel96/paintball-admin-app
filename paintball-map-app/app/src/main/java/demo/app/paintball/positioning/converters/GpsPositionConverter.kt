package demo.app.paintball.positioning.converters

import android.location.Location
import demo.app.paintball.data.rest.models.Map

class GpsPositionConverter(val map: Map) : PositionConverter() {

    override fun mmToPx(inputMm: Int): Int {
        val topLeftLocation = Location("").apply {
            latitude = map.topLeftLatitude
            longitude = map.topLeftLongitude
        }
        val topRightLocation = Location("").apply {
            latitude = map.topLeftLatitude
            longitude = map.topRightLongitude
        }
        val widthInMm = topLeftLocation.distanceTo(topRightLocation) * 1000

        return (inputMm.toFloat() / widthInMm * map.width.toFloat()).toInt()
    }
}