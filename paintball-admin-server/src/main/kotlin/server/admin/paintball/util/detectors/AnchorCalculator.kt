package server.admin.paintball.util.detectors

import server.admin.paintball.dto.AnchorDTO
import server.admin.paintball.dto.MapDTO

interface AnchorCalculator {

    fun calculateAnchors(map: MapDTO, anchorRadius: Int): List<AnchorDTO>
}
