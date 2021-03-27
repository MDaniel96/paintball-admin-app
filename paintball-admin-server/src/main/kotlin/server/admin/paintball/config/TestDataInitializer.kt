package server.admin.paintball.config

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import server.admin.paintball.model.Game
import server.admin.paintball.repository.GameRepository

@Component
@Profile("dev")
class TestDataInitializer(
    private val gameRepository: GameRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        gameRepository.saveAll(
            listOf(
                Game(name = "game1"),
                Game(name = "game2"),
                Game(name = "game3")
            )
        )
    }
}
