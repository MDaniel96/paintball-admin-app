package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.admin.paintball.model.Obstacle

interface ObstacleRepository : JpaRepository<Obstacle, Long>
