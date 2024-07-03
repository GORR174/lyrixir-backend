package net.catstack.lyrixir.repository

import net.catstack.lyrixir.entity.ArtistModel
import net.catstack.lyrixir.entity.SongModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface SongRepository : JpaRepository<SongModel, Long> {
    @Query(nativeQuery = true, value = "INSERT INTO song (artist_id, song_name, text, text_vector, created, updated) " +
            "VALUES (:#{#model.artist.id}, :#{#model.songName}, :#{#model.text}, to_tsvector(:#{#model.text}), :timestamp, :timestamp) " +
            "returning *")
    fun addSong(model: SongModel, timestamp: LocalDateTime): SongModel

    fun addSong(model: SongModel) = addSong(model, LocalDateTime.now())

    fun findByArtistIdAndTextContainsIgnoreCase(artistId: Long, searchText: String): List<SongModel>

    fun findByArtistId(artistId: Long): List<SongModel>
}