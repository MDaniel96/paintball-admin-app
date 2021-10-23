package demo.app.paintball.positioning.calculators

interface PositionCalculatorListener {
    fun onPositionCalculated(posX: Int, posY: Int)
}