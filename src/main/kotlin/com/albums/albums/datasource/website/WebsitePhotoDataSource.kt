package com.albums.albums.datasource.website

import com.albums.albums.datasource.PhotoDataSource
import com.albums.albums.model.Photo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository(value = "websitePhoto")
class WebsitePhotoDataSource(
    @Autowired private val restTemplate: RestTemplate
) : PhotoDataSource {

    override fun getPhotos(): Collection<Photo> {
        val response: ResponseEntity<Collection<Photo>> =
            restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/photos",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Collection<Photo>>() {}
            )

        return response.body
            ?: throw IOException("Could not fetch photos from website")
    }
    override fun getPhotosByAlbumId(albumId: Int): Collection<Photo> {
        if (albumId <= 0) {
            throw IllegalArgumentException("You must introduce a positive integer for albumId")
        }

        val response: ResponseEntity<Collection<Photo>> =
            restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/photos?albumId=$albumId",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Collection<Photo>>() {}
            )

        if (response.body?.isEmpty() == true) {
            throw NoSuchElementException("Could not find an album with albumId $albumId")
        }

        return response.body
            ?: throw IOException("Could not fetch photos from website")
    }
}
