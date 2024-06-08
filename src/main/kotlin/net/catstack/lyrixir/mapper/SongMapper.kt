package net.catstack.lyrixir.mapper

import net.catstack.lyrixir.dto.response.SongResponseDto
import net.catstack.lyrixir.entity.SongModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface SongMapper {
    @Mapping(target = "artistId", source = "artist.id")
    fun songModelToSong(songModel: SongModel): SongResponseDto
}