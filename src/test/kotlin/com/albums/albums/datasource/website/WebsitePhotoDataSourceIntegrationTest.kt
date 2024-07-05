package com.albums.albums.datasource.website

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebsitePhotoDataSourceIntegrationTest {

    @Autowired
    private lateinit var websiteDataSource: WebsitePhotoDataSource

    @Test
    fun `should retrieve a collection of photos by album id`() {
        val result = websiteDataSource.getPhotosByAlbumId(2)

        Assertions.assertThat(result.size).isEqualTo(50)
    }

    @Test
    fun `should call correct URL`() {
        val result = websiteDataSource.getPhotosByAlbumId(2)
        val expectedIds = (51..100).toList()

        assertThat(result.size).isEqualTo(50)
        assertThat(result).extracting("id").containsExactlyInAnyOrderElementsOf(expectedIds)
    }

    @Test
    fun `should throw NoSuchElementException for empty album`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            websiteDataSource.getPhotosByAlbumId(101)
        }

        assertThat(exception.message).isEqualTo("Could not find an album with albumId 101")
    }

    @Test
    fun `should throw IllegalArgumentException for invalid albumId`() {
        val invalidAlbumId = 0

        val exception = assertThrows(IllegalArgumentException::class.java) {
            websiteDataSource.getPhotosByAlbumId(invalidAlbumId)
        }

        assertThat(exception.message).isEqualTo("You must introduce a positive integer for albumId")
    }
}
