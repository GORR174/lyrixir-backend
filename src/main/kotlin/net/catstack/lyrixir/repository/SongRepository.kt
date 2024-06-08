package net.catstack.lyrixir.repository

import net.catstack.lyrixir.entity.SongModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface SongRepository : JpaRepository<SongModel, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE song SET text_vector = to_tsvector(:text), updated = :updated where id = :id")
    fun setTextVector(text: String, id: Long, updated: Date?)

    @Transactional
    fun addSong(songModel: SongModel): SongModel {
        val model = save(songModel)
        setTextVector(model.text ?: "", model.id, model.created)

        return model
    }
}