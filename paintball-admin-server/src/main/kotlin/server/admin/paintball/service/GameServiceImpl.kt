package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.dto.util.GameFilter
import server.admin.paintball.repository.GameRepository

@Service
class GameServiceImpl(
    private val gameRepository: GameRepository,
    private val mapper: ModelMapper
) : GameService {

    override fun getGames(gameFilter: GameFilter?): List<GameDTO> {
        val gameList = gameFilter?.run {
            gameRepository.findAllByStateAndNameContainsAndTypeInAndDate(state, name, types, date)
        } ?: gameRepository.findAll()

        return gameList.toDTO(mapper)
    }
}
