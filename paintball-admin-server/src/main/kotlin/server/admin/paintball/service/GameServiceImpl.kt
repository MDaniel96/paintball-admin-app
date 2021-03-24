package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.repository.GameRepository

@Service
class GameServiceImpl(
    private val gameRepository: GameRepository,
    private val mapper: ModelMapper
) : GameService {

    override fun getGames(): List<GameDTO> {
        return gameRepository.findAll().toDTO(mapper)
    }
}
