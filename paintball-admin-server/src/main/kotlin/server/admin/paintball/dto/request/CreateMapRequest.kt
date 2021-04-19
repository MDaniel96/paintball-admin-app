package server.admin.paintball.dto.request

class CreateMapRequest(

    val locationId: Long = -1L,
    val userId: Long = -1L,
    val name: String = "",
    val imageBase64: String = "",
    val orientation: Int = 0,
    val width: Long = 0,
    val height: Long = 0
)
