package net.catstack.lyrixir.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import net.catstack.lyrixir.entity.SongModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

interface CustomSongRepository {
    fun addSong(songModel: SongModel): SongModel
}

open class CustomSongRepositoryImpl : CustomSongRepository {
    @PersistenceContext
    lateinit var em: EntityManager

    @Transactional
    override fun addSong(songModel: SongModel): SongModel {
        val insertQuery = em.createNativeQuery("insert into song(artist_id, song_name, text, text_vector, created, updated) values " +
                "(:artistId, :songName, :text, to_tsvector(:text), :created, :updated) RETURNING id")
            .setParameter("artistId", songModel.artist?.id)
            .setParameter("songName", songModel.songName)
            .setParameter("text", songModel.text)
            .setParameter("created", LocalDateTime.now())
            .setParameter("updated", LocalDateTime.now())

        val id = insertQuery.singleResult
        val model = em.find(SongModel::class.java, id)

        return model
    }
}

@Repository
interface SongRepository : JpaRepository<SongModel, Long>, CustomSongRepository {

}