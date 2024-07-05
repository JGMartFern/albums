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
class WebsiteAlbumDataSourceIntegrationTest {

    @Autowired
    private lateinit var websiteDataSource: WebsiteAlbumDataSource

    @Test
    fun `should retrieve a collection of albums`() {

        val result = websiteDataSource.getAlbums()

        Assertions.assertThat(result).isNotEmpty()
    }
}
