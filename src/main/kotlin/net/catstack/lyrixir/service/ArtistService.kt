package net.catstack.lyrixir.service

import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.dto.response.ArtistDto
import net.catstack.lyrixir.dto.response.GetArtistsResponseDto
import net.catstack.lyrixir.mapper.ArtistMapper
import net.catstack.lyrixir.repository.ArtistRepository
import net.catstack.lyrixir.util.injectLogger
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ArtistService(
    val artistRepository: ArtistRepository,
    val mapper: ArtistMapper
) {
    val logger = injectLogger()

    fun addArtist(request: AddArtistRequestDto): AddArtistResponseDto {
        var artistModel = mapper.addArtistDtoToEntity(request)
        artistModel = artistRepository.save(artistModel);

        val response = mapper.entityToAddArtistDto(artistModel)
        logger.info("Create new artist: ${response.name}")

        return response
    }

    fun getArtists(page: Int, size: Int): GetArtistsResponseDto {
        val artistModels = artistRepository.findAll(PageRequest.of(page, size)).content

        return mapper.entityListToDto(artistModels)
    }

    fun getArtist(id: Long): ArtistDto {
        return mapper.entityToDto(artistRepository.findById(id).orElse(null))
    }
}