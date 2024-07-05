package com.albums.albums.datasource.website

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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
