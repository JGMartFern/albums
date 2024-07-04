package com.albums.albums.datasource.website

import com.albums.albums.datasource.AlbumDataSource
import com.albums.albums.model.Album
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository(value = "website")
class WebsiteAlbumDataSource(
    @Autowired private val restTemplate: RestTemplate
) : AlbumDataSource {
    override fun getAlbums(): Collection<Album> {
        val response: ResponseEntity<Collection<Album>> =
            restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/albums",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Collection<Album>>() {}
            )

        return response.body
            ?: throw IOException ("Could not fetch albums from website")
    }

}