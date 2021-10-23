package demo.app.paintball.positioning.converters

import demo.app.paintball.data.rest.models.Game

abstract class PositionConverter {

    companion object {
        fun create(game: Game): PositionConverter {
            return when (game.localizationMode) {
                Game.LocalizationMode.GPS -> GpsPositionConverter(game.map!!)
                Game.LocalizationMode.UWB -> UwbPositionConverter(game.map!!)
            }
        }
    }

    abstract fun mmToPx(inputMm: Int): Int
}