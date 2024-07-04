package com.albums.albums.datasource.mock

import com.albums.albums.datasource.PhotoDataSource
import com.albums.albums.model.Album
import com.albums.albums.model.Photo
import org.springframework.stereotype.Repository

@Repository
class MockPhotoDataSource : PhotoDataSource {

    val photos = listOf(
        Photo(2, 1, "Mary", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
        Photo(2, 4, "Maureen", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
        Photo(2, 10, "Misty", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
        Photo(2, 21, "Monica", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
    )

    override fun getPhotosByAlbumId(albumId: Int): Collection<Photo> = photos
}