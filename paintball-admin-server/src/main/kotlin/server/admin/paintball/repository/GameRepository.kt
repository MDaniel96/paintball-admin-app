package server.admin.paintball.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import server.admin.paintball.model.Game
import java.time.LocalDate

interface GameRepository : JpaRepository<Game, Long> {

    @Query(
        """
            SELECT g FROM Game g 
            WHERE (:state is null or g.state = :state) 
                AND (:name is null or g.name LIKE %:name%)
                AND ((:types) is null or g.type IN :types)
                AND (:date is null or g.date = :date)
         """
    )
    fun findAllByStateAndNameContainsAndTypeInAndDate(
        @Param("state") state: Game.State?,
        @Param("name") name: String?,
        @Param("types") types: List<String>?,
        @Param("date") date: LocalDate?
    ): List<Game>
}
