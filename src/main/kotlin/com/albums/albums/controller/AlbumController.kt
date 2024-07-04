package com.albums.albums.controller

import com.albums.albums.model.Album
import com.albums.albums.service.AlbumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/albums")
class AlbumController (private val service: AlbumService) {

    @GetMapping
    fun getAlbums(): Collection<Album> = service.getAlbums()
}