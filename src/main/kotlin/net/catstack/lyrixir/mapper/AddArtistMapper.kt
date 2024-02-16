package net.catstack.lyrixir.mapper

import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.entity.ArtistModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AddArtistMapper {
    fun dtoToEntity(dto: AddArtistRequestDto): ArtistModel
    fun entityToDto(dto: ArtistModel): AddArtistResponseDto
}