package net.catstack.lyrixir.controller

import net.catstack.lyrixir.dto.AdapterResponse
import net.catstack.lyrixir.dto.request.AddArtistRequestDto
import net.catstack.lyrixir.dto.response.AddArtistResponseDto
import net.catstack.lyrixir.dto.response.GetArtistsResponseDto
import net.catstack.lyrixir.service.ArtistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// TODO: 404 fix
@RestController
@RequestMapping("/artist")
class ArtistController(
    val artistService: ArtistService
) {
    @PostMapping("/addArtist")
    fun addArtist(@RequestBody request: AddArtistRequestDto): AdapterResponse<AddArtistResponseDto> {
        return AdapterResponse.success(artistService.addArtist(request))
    }

    @GetMapping("/artists")
    fun getArtists(@RequestParam("page", defaultValue = "0") page: Int,
                   @RequestParam("size", defaultValue = "20") size: Int): AdapterResponse<GetArtistsResponseDto> {
        return AdapterResponse.success(artistService.getArtists(page, size))
    }

}