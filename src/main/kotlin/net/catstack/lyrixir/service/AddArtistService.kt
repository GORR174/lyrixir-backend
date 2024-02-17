package net.catstack.lyrixir.service

import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.mapper.AddArtistMapper
import net.catstack.lyrixir.repository.ArtistRepository
import net.catstack.lyrixir.util.injectLogger
import org.springframework.stereotype.Service

@Service
class AddArtistService(
    val artistRepository: ArtistRepository,
    val mapper: AddArtistMapper
) {
    val logger = injectLogger()

    fun addArtist(request: AddArtistRequestDto): AddArtistResponseDto {
        var artistModel = mapper.dtoToEntity(request)
        artistModel = artistRepository.save(artistModel);

        val response = mapper.entityToDto(artistModel)
        logger.info("Create new artist: ${response.name}")

        return response
    }
}