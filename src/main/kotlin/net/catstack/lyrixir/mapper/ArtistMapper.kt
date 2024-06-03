package net.catstack.lyrixir.mapper

import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.dto.response.ArtistDto
import net.catstack.lyrixir.dto.response.GetArtistsResponseDto
import net.catstack.lyrixir.entity.ArtistModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ArtistMapper {
    fun addArtistDtoToEntity(dto: AddArtistRequestDto): ArtistModel
    fun entityToAddArtistDto(dto: ArtistModel): AddArtistResponseDto
    fun entityListToDtoList(artist: List<ArtistModel>): List<ArtistDto>
    @Mapping(target = "artists", source = "entityList")
    fun entityListToDto(entityList: List<ArtistModel>, dummy: Int = 0): GetArtistsResponseDto
}