package server.admin.paintball.config

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import server.admin.paintball.model.Game
import server.admin.paintball.repository.GameRepository
import java.time.LocalDate

@Component
@Profile("dev")
class TestDataInitializer(
    private val gameRepository: GameRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        gameRepository.saveAll(
            listOf(
                Game(name = "TDM game1", type = "TDM", state = Game.State.FINISHED),
                Game(name = "TDM game2", type = "TDM", state = Game.State.FINISHED),
                Game(name = "DM game3", type = "DM", state = Game.State.FINISHED),
                Game(name = "DM game4", type = "DM", state = Game.State.CREATED, date = LocalDate.now().minusDays(1))
            )
        )
    }
}
