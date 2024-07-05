package com.albums.albums.controller

import com.albums.albums.model.Photo
import com.albums.albums.service.PhotoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Photos", description = "API for retrieving Photos from JSONPlaceholder given an AlbumId")
@RestController
@RequestMapping("/api/photos")
class PhotoController(private val service: PhotoService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleAlbumNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNumberNotValid(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    @Operation(
        summary = "It obtains photos with an AlbumId",
        description = "It returns a collection of all available photos with a matching AlbumId"
    )
    @GetMapping
    fun getPhotosByAlbumId(@RequestParam(value = "albumId") albumId: Int): Collection<Photo> = service.getPhotosByAlbumId(albumId)
}
