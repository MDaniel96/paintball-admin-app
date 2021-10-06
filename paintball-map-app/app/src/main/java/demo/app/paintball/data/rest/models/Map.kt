package demo.app.paintball.data.rest.models

class Map(
    val id: Long = -1L,
    var name: String = "",
    var orientation: Int = 0,
    var width: Long = 0,
    var height: Long = 0,
    var borderX: Long = -1L,
    var borderY: Long = -1L,
    var borderWidth: Long = -1L,
    var borderHeight: Long = -1L,
    var anchorRadiusInMm: Long = -1L,
    var anchorRadiusInPixels: Long = -1L,
    var location: Location? = null,
    var obstacles: MutableSet<Obstacle> = hashSetOf(),
    var anchors: MutableSet<Anchor> = hashSetOf()
)

class Location(
    val id: Long = -1L,
    var name: String = ""
)

class Obstacle(
    val id: Long = -1L,
    var x: Long = -1L,
    var y: Long = -1L,
    var width: Long = -1L,
    var height: Long = -1L
)

class Anchor(
    val id: Long = -1L,
    var x: Long = -1L,
    var y: Long = -1L,
    var radius: Long = -1L
)