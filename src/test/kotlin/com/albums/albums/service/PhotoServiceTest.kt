package com.albums.albums.service

import com.albums.albums.datasource.PhotoDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PhotoServiceTest {

    private val dataSource: PhotoDataSource = mockk(relaxed = true)
    private val photoService = PhotoService(dataSource)

    @Test
    fun `should call the data source to get the photos`() {
        val albumId = 3
        val photos = photoService.getPhotosByAlbumId(albumId)

        verify(exactly = 1) { dataSource.getPhotosByAlbumId(albumId) }
    }
}
