package server.admin.paintball.config

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import server.admin.paintball.model.*
import server.admin.paintball.model.Map
import server.admin.paintball.repository.*
import server.admin.paintball.security.authorization.RoleService
import java.time.LocalDate

@Component
@Profile("dev")
class TestDataInitializer(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository,
    private val mapRepository: MapRepository,
    private val anchorRepository: AnchorRepository,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder
) : ApplicationRunner {

    companion object {
        val TODAY: LocalDate = LocalDate.now()
        val YESTERDAY: LocalDate = LocalDate.now().minusDays(1)
    }

    @Transactional
    override fun run(args: ApplicationArguments?) {
        val games = gameRepository.saveAll(
            listOf(
                Game(
                    name = "Mogyorod (GPS) TDM game1",
                    type = "TDM",
                    state = Game.State.CREATED,
                    date = TODAY,
                    localizationMode = Game.LocalizationMode.GPS
                ),
                Game(
                    name = "TDM game2",
                    type = "TDM",
                    state = Game.State.STARTED,
                    date = TODAY,
                    localizationMode = Game.LocalizationMode.UWB
                ),
                Game(name = "DM game3", type = "DM", state = Game.State.FINISHED, date = TODAY),
                Game(name = "DM game4", type = "DM", state = Game.State.FINISHED, date = TODAY),
                Game(name = "DM game5", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "DM game6", type = "CTF", state = Game.State.CREATED, date = YESTERDAY),
                Game(
                    name = "Gyenes (UWB) DM game7",
                    type = "DM",
                    state = Game.State.CREATED,
                    date = YESTERDAY,
                    localizationMode = Game.LocalizationMode.UWB
                ),
                Game(name = "DM game8", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game9", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
                Game(name = "TDM game10", type = "TDM", state = Game.State.STARTED, date = YESTERDAY),
                Game(
                    name = "TDM game11",
                    type = "TDM",
                    state = Game.State.FINISHED,
                    date = YESTERDAY,
                    localizationMode = Game.LocalizationMode.UWB
                ),
                Game(name = "TDM game12", type = "TDM", state = Game.State.FINISHED, date = YESTERDAY),
            )
        )

        val users = userRepository.saveAll(
            listOf(
                User(
                    username = "User1",
                    password = passwordEncoder.encode("player"),
                    roles = mutableSetOf(roleService.player)
                ),
                User(
                    username = "User2",
                    password = passwordEncoder.encode("player"),
                    roles = mutableSetOf(roleService.player)
                ),
                User(
                    username = "User3",
                    password = passwordEncoder.encode("player"),
                    roles = mutableSetOf(roleService.player)
                ),
                User(
                    username = "User4",
                    password = passwordEncoder.encode("player"),
                    roles = mutableSetOf(roleService.player)
                ),
                User(
                    username = "User5",
                    password = passwordEncoder.encode("player"),
                    roles = mutableSetOf(roleService.player)
                ),
                User(
                    username = "admin",
                    password = passwordEncoder.encode("admin"),
                    roles = mutableSetOf(roleService.admin)
                )
            )
        )

        val locations = locationRepository.saveAll(
            listOf(
                Location(name = "Location1"),
                Location(name = "Location2"),
                Location(name = "Location3")
            )
        )

        val maps = mapRepository.saveAll(
            listOf(
                createUWBMapGyenes(),
                createGPSMapMogyorod(),
                Map(name = "Map1", borderX = 10, borderY = 10, borderWidth = 10, borderHeight = 10),
                Map(name = "Map2", borderX = 20, borderY = 20, borderWidth = 20, borderHeight = 20),
                Map(name = "Map3", borderX = 30, borderY = 30, borderWidth = 30, borderHeight = 30)
            )
        )

        val anchors = anchorRepository.saveAll(
            listOf(
                Anchor(x = 20_000, y = 20_000),
                Anchor(x = 40_000, y = 20_000),
                Anchor(x = 20_000, y = 40_000),
                Anchor(x = 40_000, y = 40_000),
            )
        )

        games[3].redPlayers.add(users[0])
        games[3].redPlayers.add(users[1])
        games[3].bluePlayers.add(users[2])

        games[5].redPlayers.add(users[0])
        games[5].redPlayers.add(users[1])
        games[5].bluePlayers.add(users[2])

        games[9].redPlayers.add(users[0])
        games[9].redPlayers.add(users[1])
        games[9].bluePlayers.add(users[2])

        maps[0].location = locations[0]
        maps[1].location = locations[0]

        games[1].map = maps[0]

        games[0].apply {
            map = maps[1]
            redPlayers.add(users[1])
            redPlayers.add(users[2])
            bluePlayers.add(users[3])
            bluePlayers.add(users[4])
            deadPlayers.add(users[3])
        }

        games[6].apply {
            map = maps[0]
            redPlayers.add(users[1])
            redPlayers.add(users[2])
            bluePlayers.add(users[3])
            bluePlayers.add(users[4])
            deadPlayers.add(users[3])
            maps[0].anchors.addAll(anchors)
            anchors[0].map = maps[0]
            anchors[1].map = maps[0]
            anchors[2].map = maps[0]
            anchors[3].map = maps[0]
        }

        gameRepository.saveAll(games)
        userRepository.saveAll(users)
        locationRepository.saveAll(locations)
        mapRepository.saveAll(maps)
        anchorRepository.saveAll(anchors)
    }

    private fun createUWBMapGyenes() = Map(
        id = 1,
        name = "Gyenes",
        width = 2968,
        orientation = 270,
        anchorRadiusInMm = 20_000,
        anchorRadiusInPixels = 1000
    )

    private fun createGPSMapMogyorod() = Map(
        id = 2,
        name = "Mogyorod",
        width = 3028,
        orientation = 0,
        topLeftLatitude = 47.603597,
        topLeftLongitude = 19.239669,
        topRightLongitude = 19.243687
    )
}
