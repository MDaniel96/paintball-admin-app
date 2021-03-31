package server.admin.paintball.service

import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.admin.paintball.dto.GameDTO
import server.admin.paintball.dto.toDTO
import server.admin.paintball.dto.util.GameFilter
import server.admin.paintball.model.Game
import server.admin.paintball.repository.GameRepository
import server.admin.paintball.util.exceptions.EntityNotFoundException

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

    override fun getGame(id: Long): GameDTO {
        return getGameById(id).toDTO(mapper)
    }

    @Transactional
    override fun deleteGame(id: Long) {
        val game = getGameById(id)
        gameRepository.delete(game)
    }

    @Transactional
    override fun editGame(id: Long, game: GameDTO): GameDTO {
        return getGameById(id).apply {
            name = game.name
            type = game.type
            state = game.state
            date = game.date
        }.run {
            toDTO(mapper)
        }
    }

    private fun getGameById(id: Long): Game {
        return gameRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Game not found")
    }
}
