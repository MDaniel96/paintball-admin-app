package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.admin.paintball.model.Map

interface MapRepository : JpaRepository<Map, Long>
