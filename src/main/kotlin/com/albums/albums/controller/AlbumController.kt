package com.albums.albums.controller

import com.albums.albums.model.Album
import com.albums.albums.service.AlbumService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Albums", description = "API for retrieving Albums from JSONPlaceholder")
@RestController
@RequestMapping("/api/albums")
class AlbumController(private val service: AlbumService) {
    @Operation(summary = "It obtains all albums", description = "It returns a collection of all available albums")
    @GetMapping
    fun getAlbums(): Collection<Album> = service.getAlbums()
}