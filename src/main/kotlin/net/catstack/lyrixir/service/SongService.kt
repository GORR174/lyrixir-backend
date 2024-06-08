package net.catstack.lyrixir.service

import net.catstack.lyrixir.dto.request.AddSongRequestDto
import net.catstack.lyrixir.dto.response.SongResponseDto
import net.catstack.lyrixir.entity.SongModel
import net.catstack.lyrixir.mapper.SongMapper
import net.catstack.lyrixir.repository.ArtistRepository
import net.catstack.lyrixir.repository.SongRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SongService(
    private val songRepository: SongRepository,
    private val artistRepository: ArtistRepository,
    private val songMapper: SongMapper,
) {
    @Transactional
    fun addSong(requestDto: AddSongRequestDto): SongResponseDto {
        val songModel = songRepository.addSong(SongModel().apply {
            songName = requestDto.songName
            artist = artistRepository.findById(requestDto.artistId).get()
            text = requestDto.text
        })

        return songMapper.songModelToSong(songModel)
    }
}