package demo.app.paintball.positioning.calculators.uwb

import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.positioning.calculators.PositionCalculatorListener

interface UwbPositionCalculator {

    var listener: PositionCalculatorListener

    fun calculate(data: BlePositionData)
}