package com.albums.albums.controller

import com.albums.albums.model.Photo
import com.albums.albums.service.PhotoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/photos")
class PhotoController(private val service: PhotoService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleAlbumNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getPhotosByAlbumId(@RequestParam(value = "albumId") albumId: Int): Collection<Photo> = service.getPhotosByAlbumId(albumId)
}
