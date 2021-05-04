package server.admin.paintball.util.detectors

import org.springframework.stereotype.Service
import server.admin.paintball.dto.AnchorDTO
import server.admin.paintball.dto.MapDTO

@Service
class AnchorCalculatorImpl : AnchorCalculator {

    override fun calculateAnchors(map: MapDTO, anchorRadius: Int): List<AnchorDTO> {
        val anchors = mutableListOf<AnchorDTO>()
        var shift = false
        var id = 0L
        var y = map.borderY
        while (y <= map.borderY + map.borderHeight) {
            val shiftX = if (shift) anchorRadius / 2 else 0
            var x = map.borderX + shiftX
            while (x <= map.borderX + map.borderWidth) {
                anchors.add(
                    AnchorDTO(id++, x, y, anchorRadius.toLong())
                )
                x += anchorRadius
            }
            shift = !shift
            y += anchorRadius
        }
        return anchors
    }
}
