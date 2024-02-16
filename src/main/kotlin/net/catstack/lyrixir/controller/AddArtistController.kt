package net.catstack.lyrixir.controller

import net.catstack.lyrixir.dto.AdapterResponse
import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.service.AddArtistService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO: 404 fix
@RestController
@RequestMapping("/artist")
class AddArtistController(
    val addArtistService: AddArtistService
) {
    @PostMapping("/addArtist")
    fun addAuthor(@RequestBody request: AddArtistRequestDto): AdapterResponse<AddArtistResponseDto> {
        return AdapterResponse.success(addArtistService.addArtist(request))
    }
}