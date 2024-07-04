package com.albums.albums.datasource

import com.albums.albums.model.Photo

interface PhotoDataSource {

    fun getPhotosByAlbumId(albumId: Int): Collection<Photo>
}