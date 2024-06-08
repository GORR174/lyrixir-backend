package net.catstack.lyrixir.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "artist")
open class ArtistModel : BaseModel() {
    open var name: String? = null
    @Column(name = "profile_img")
    open var profileImage: String? = null

    override fun toString(): String {
        return "ArtistModel(name=$name, profileImage=$profileImage)"
    }
}