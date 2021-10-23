package demo.app.paintball.positioning.converters

import demo.app.paintball.data.rest.models.Map

class UwbPositionConverter(val map: Map) : PositionConverter() {

    override fun mmToPx(inputMm: Int): Int {
        return (inputMm.toFloat() / map.anchorRadiusInMm.toFloat() * map.anchorRadiusInPixels.toFloat()).toInt()
    }
}