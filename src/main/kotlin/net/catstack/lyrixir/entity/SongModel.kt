package net.catstack.lyrixir.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "song")
class SongModel : BaseModel() {
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    open var artist: ArtistModel? = null
    open var songName: String? = null
    open var text: String? = null

    override fun toString(): String {
        return "SongModel(artist=$artist, songName=$songName, text=$text)"
    }
}