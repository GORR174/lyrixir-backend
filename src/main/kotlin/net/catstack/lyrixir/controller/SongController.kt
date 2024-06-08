package net.catstack.lyrixir.controller

import net.catstack.lyrixir.dto.AdapterResponse
import net.catstack.lyrixir.dto.request.AddSongRequestDto
import net.catstack.lyrixir.dto.response.SongResponseDto
import net.catstack.lyrixir.service.SongService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/song")
class SongController(val songService: SongService) {
    @PostMapping("/addSong")
    fun addSong(@RequestBody requestDto: AddSongRequestDto): AdapterResponse<SongResponseDto> {
        return AdapterResponse.success(songService.addSong(requestDto))
    }
}