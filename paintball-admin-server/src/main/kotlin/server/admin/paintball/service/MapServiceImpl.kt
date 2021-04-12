package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import server.admin.paintball.dto.MapDTO
import server.admin.paintball.dto.request.CreateMapRequest
import server.admin.paintball.dto.toDTO
import server.admin.paintball.model.Map
import server.admin.paintball.repository.MapRepository
import java.io.File
import java.util.*
import javax.transaction.Transactional

@Service
class MapServiceImpl(
    private val mapRepository: MapRepository,
    private val mapper: ModelMapper,
    private val locationService: LocationService,
    private val userService: UserService
) : MapService {

    @Value("\${map.image.location}")
    private val mapImageLocation: String = ""

    @Transactional
    override fun save(createMapRequest: CreateMapRequest): MapDTO {
        createMapRequest.run {
            val imageName = "${name.replace("\\s".toRegex(), "")}.png"
            val map = mapRepository.save(
                Map(
                    name = name,
                    imageUrl = "map/image/${imageName}",
                    orientation = orientation,
                    location = locationService.getLocationById(locationId)
                )
            )
            saveImage(imageBase64, imageName)
            val user = userService.getUserById(userId)
            user.addMapUnderCreation(map)
            return map.toDTO(mapper)
        }
    }

    override fun getImage(imageName: String): ByteArray {
        return File("${mapImageLocation}//${imageName}").readBytes()
    }

    private fun saveImage(imageBase64: String, imageName: String) {
        val imageByteArray = Base64.getDecoder().decode(imageBase64)
        File("${mapImageLocation}//${imageName.replace("\\s".toRegex(), "")}").writeBytes(imageByteArray)
    }
}
