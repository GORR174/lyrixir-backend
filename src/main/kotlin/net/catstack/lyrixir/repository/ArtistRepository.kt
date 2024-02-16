package net.catstack.lyrixir.repository

import net.catstack.lyrixir.entity.ArtistModel
import org.springframework.data.jpa.repository.JpaRepository

interface ArtistRepository : JpaRepository<ArtistModel, Long> {

}