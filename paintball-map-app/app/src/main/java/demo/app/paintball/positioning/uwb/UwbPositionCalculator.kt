package demo.app.paintball.positioning.uwb

import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.positioning.PositionCalculatorListener

interface UwbPositionCalculator {

    var listener: PositionCalculatorListener

    fun calculate(data: BlePositionData)
}