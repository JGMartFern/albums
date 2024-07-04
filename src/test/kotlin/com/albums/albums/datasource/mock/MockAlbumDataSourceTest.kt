package com.albums.albums.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MockAlbumDataSourceTest {

    private val mockDataSource = MockAlbumDataSource()

    @Test
    fun `should retrieve a collection of albums`() {
        val albums = mockDataSource.getAlbums()

        Assertions.assertThat(albums.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should get some mock data`() {
        val albums = mockDataSource.getAlbums()

        Assertions.assertThat(albums).allMatch { it.userId > 0 }
        Assertions.assertThat(albums).allMatch { it.id > 0 }
        Assertions.assertThat(albums).allMatch { it.title.isNotBlank() }
    }
}
