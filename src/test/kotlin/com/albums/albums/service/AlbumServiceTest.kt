package com.albums.albums.service

import com.albums.albums.datasource.AlbumDataSource
import com.albums.albums.datasource.PhotoDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AlbumServiceTest {

    private val albumDataSource: AlbumDataSource = mockk(relaxed = true)
    private val photoDataSource: PhotoDataSource = mockk(relaxed = true)
    private val albumService = AlbumService(albumDataSource, photoDataSource)

    @Test
    fun `should call the data source to get the albums`() {
        val albums = albumService.getAlbums()

        verify(exactly = 1) { albumDataSource.getAlbums() }
    }
}
