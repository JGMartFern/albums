package com.albums.albums.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MockPhotoDataSourceTest {

    private val mockDataSource = MockPhotoDataSource()

    @Test
    fun `should retrieve a collection of photos`() {
        // when
        val albums = mockDataSource.getPhotosByAlbumId(2)

        // then
        Assertions.assertThat(albums.size).isEqualTo(2)
    }

    @Test
    fun `should get some mock data`() {
        // when
        val albums = mockDataSource.getPhotosByAlbumId(2)

        // then
        Assertions.assertThat(albums).allMatch { it.albumId > 0 }
        Assertions.assertThat(albums).allMatch { it.id > 0 }
        Assertions.assertThat(albums).allMatch { it.title.isNotBlank() }
        Assertions.assertThat(albums).allMatch { it.url.isNotBlank() }
        Assertions.assertThat(albums).allMatch { it.thumbnailUrl.isNotBlank() }
    }
}
