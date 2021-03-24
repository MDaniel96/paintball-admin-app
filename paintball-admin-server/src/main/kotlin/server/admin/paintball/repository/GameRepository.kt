package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.admin.paintball.model.Game

interface GameRepository : JpaRepository<Game, Long>
