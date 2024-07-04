package com.albums.albums.service

import com.albums.albums.datasource.AlbumDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AlbumServiceTest {

    private val dataSource: AlbumDataSource = mockk(relaxed = true)
    private val albumService = AlbumService(dataSource)

    @Test
    fun `should call the data source to get the albums`() {
        //when
        val albums = albumService.getAlbums()

        //then
        verify(exactly = 1) { dataSource.getAlbums() }
        }
}