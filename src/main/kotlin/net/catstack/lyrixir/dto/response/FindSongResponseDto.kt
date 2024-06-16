package net.catstack.lyrixir.dto.response

data class FindSongResponseDto(val songs: List<SongWithSearchIndex>)

data class SongWithSearchIndex(val song: SongResponseDto, val indexes: List<Long>)