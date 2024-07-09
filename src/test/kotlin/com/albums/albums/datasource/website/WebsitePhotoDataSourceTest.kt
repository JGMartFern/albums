package com.albums.albums.datasource.website

import com.albums.albums.model.Photo
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@SpringBootTest
class WebsitePhotoDataSourceTest {

    @MockBean
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var websiteDataSource: WebsitePhotoDataSource

    @Test
    fun `should retrieve a collection of photos`() {
        val photos: Collection<Photo> = listOf(
            Photo(1, 1, "Photo 1", "sampleUrl", "sampleThumbnailUrl"),
            Photo(1, 2, "Photo 2", "sampleUrl", "sampleThumbnailUrl"),
            Photo(1, 3, "Photo 3", "sampleUrl", "sampleThumbnailUrl")
        )

        val responseEntity = ResponseEntity(photos, HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any<ParameterizedTypeReference<Collection<Photo>>>()
            )
        ).thenReturn(responseEntity)

        val result = websiteDataSource.getPhotos()

        Assertions.assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun `should retrieve a collection of photos by album id`() {
        val photos: Collection<Photo> = listOf(
            Photo(
                2,
                51,
                "Photo1",
                "https://via.placeholder.com/600/8e973b",
                "https://via.placeholder.com/150/8e973b"
            ),
            Photo(
                2,
                55,
                "Photo2",
                "https://via.placeholder.com/600/5e04a4",
                "https://via.placeholder.com/150/5e04a4"
            ),
            Photo(
                2,
                3,
                "Photo3",
                "https://via.placeholder.com/150/5e04a4",
                "https://via.placeholder.com/150/5e04a4"
            )
        )

        val responseEntity = ResponseEntity(photos, HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any<ParameterizedTypeReference<Collection<Photo>>>()
            )
        ).thenReturn(responseEntity)

        val result = websiteDataSource.getPhotosByAlbumId(2)

        Assertions.assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun `should call correct URL`() {
        val albumId = 2
        val photos: Collection<Photo> = listOf(
            Photo(
                2,
                51,
                "Photo1",
                "https://via.placeholder.com/600/8e973b",
                "https://via.placeholder.com/150/8e973b"
            ),
            Photo(
                2,
                55,
                "Photo2",
                "https://via.placeholder.com/600/5e04a4",
                "https://via.placeholder.com/150/5e04a4"
            )
        )

        val responseEntity = ResponseEntity(photos, HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/photos?albumId=$albumId"),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any<ParameterizedTypeReference<Collection<Photo>>>()
            )
        ).thenReturn(responseEntity)

        val result = websiteDataSource.getPhotosByAlbumId(albumId)

        Assertions.assertThat(result.size).isEqualTo(2)
        Assertions.assertThat(result).containsExactlyInAnyOrderElementsOf(photos)
    }

    @Test
    fun `should throw NoSuchElementException for empty album`() {
        val photos: Collection<Photo> = emptyList()
        val responseEntity = ResponseEntity(photos, HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any<ParameterizedTypeReference<Collection<Photo>>>()
            )
        ).thenReturn(responseEntity)

        val exception = assertThrows(NoSuchElementException::class.java) {
            websiteDataSource.getPhotosByAlbumId(2)
        }

        Assertions.assertThat(exception.message).isEqualTo("Could not find an album with albumId 2")
    }

    @Test
    fun `should throw IllegalArgumentException for invalid albumId`() {
        val invalidAlbumId = 0

        val exception = assertThrows(IllegalArgumentException::class.java) {
            websiteDataSource.getPhotosByAlbumId(invalidAlbumId)
        }

        Assertions.assertThat(exception.message).isEqualTo("You must introduce a positive integer for albumId")
    }
}
