package com.albums.albums.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MockAlbumDataSourceTest {

    private val mockDataSource = MockAlbumDataSource()

    @Test
    fun `should retrieve a collection of albums`() {
        //given

        //when
        val albums = mockDataSource.getAlbums()

        //then
        Assertions.assertThat(albums.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should get some mock data`() {

        //when
        val albums = mockDataSource.getAlbums()

        //then
        Assertions.assertThat(albums).allMatch { it.userId > 0 }
        Assertions.assertThat(albums).allMatch { it.id > 0 }
        Assertions.assertThat(albums).allMatch { it.title.isNotBlank() }

    }

}