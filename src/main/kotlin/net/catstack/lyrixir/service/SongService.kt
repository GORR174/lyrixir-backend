package net.catstack.lyrixir.service

import net.catstack.lyrixir.dto.request.AddSongRequestDto
import net.catstack.lyrixir.dto.request.FindSongRequestDto
import net.catstack.lyrixir.dto.response.FindSongResponseDto
import net.catstack.lyrixir.dto.response.SongResponseDto
import net.catstack.lyrixir.dto.response.SongWithSearchIndex
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

    fun findSong(requestDto: FindSongRequestDto): FindSongResponseDto {
        val songsWithIndexes = songRepository.findByArtistIdAndTextContainsIgnoreCase(requestDto.artistId, requestDto.searchText)
            .map(songMapper::songModelToSong)
            .map { song ->
                SongWithSearchIndex(song, findSongIndexes(song.text ?: "", requestDto.searchText))
            }

        return FindSongResponseDto(songsWithIndexes)
    }

    private fun findSongIndexes(text: String, searchText: String): List<Long> {
        return searchText.toRegex(setOf(RegexOption.IGNORE_CASE))
            .findAll(text)
            .map { it.range.first.toLong() }
            .toList()
    }

    fun findAllSongNames(artistId: Long): List<SongResponseDto> {
        return songRepository.findByArtistId(artistId)
            .map {
                SongResponseDto(it.id, artistId, it.songName ?: throw RuntimeException("Song name not found"), null)
            }
            .toList()
    }

    fun getById(id: Long): SongResponseDto {
        return songMapper.songModelToSong(songRepository.findById(id).orElseThrow { RuntimeException("Song not found") })
    }
}