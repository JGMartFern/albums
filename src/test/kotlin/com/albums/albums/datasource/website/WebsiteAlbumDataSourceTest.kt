package com.albums.albums.datasource.website

import com.albums.albums.model.Album
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
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
class WebsiteAlbumDataSourceTest {

    @MockBean
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var websiteDataSource: WebsiteAlbumDataSource

    @Test
    fun `should retrieve a collection of albums`() {

        val albums: Collection<Album> = listOf(
            Album(1, 1, "Album 1"),
            Album(1, 2, "Album 2"),
            Album(1, 3, "Album 3")
        )

        val responseEntity = ResponseEntity(albums, HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any<ParameterizedTypeReference<Collection<Album>>>()
            )
        ).thenReturn(responseEntity)

        val result = websiteDataSource.getAlbums()

        Assertions.assertThat(result.size).isEqualTo(3)
    }
}