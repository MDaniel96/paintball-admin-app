package server.admin.paintball.config

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import server.admin.paintball.model.Game
import server.admin.paintball.model.User
import server.admin.paintball.repository.GameRepository
import server.admin.paintball.repository.UserRepository
import java.time.LocalDate

@Component
@Profile("dev")
class TestDataInitializer(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository
) : ApplicationRunner {

    companion object {
        val TODAY: LocalDate = LocalDate.now()
        val YESTERDAY: LocalDate = LocalDate.now().minusDays(1)
    }

    override fun run(args: ApplicationArguments?) {
        val games = gameRepository.saveAll(
            listOf(
                Game(name = "TDM game1", type = "TDM", state = Game.State.FINISHED, date = TODAY),
                Game(name = "TDM game2", type = "TDM", state = Game.State.FINISHED, date = TODAY),
                Game(name = "DM game3", type = "DM", state = Game.State.FINISHED, date = TODAY),
                Game(name = "DM game4", type = "DM", state = Game.State.CREATED, date = TODAY),
                Game(name = "DM game5", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "DM game6", type = "CTF", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "DM game7", type = "DM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "DM game8", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game9", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game10", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game11", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game12", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
            )
        )

        val users = userRepository.saveAll(
            listOf(
                User(name = "User1"),
                User(name = "User2"),
                User(name = "User3")
            )
        )

        games[0].run {
            addRedPlayer(users[0])
            addRedPlayer(users[1])
            addBluePlayer(users[2])
        }
        gameRepository.saveAll(games)
        userRepository.saveAll(users)
    }
}
