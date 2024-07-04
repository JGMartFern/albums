package com.albums.albums.service

import com.albums.albums.datasource.PhotoDataSource
import com.albums.albums.model.Photo
import org.springframework.stereotype.Service

@Service
class PhotoService (private val dataSource: PhotoDataSource) {
    fun getPhotosByAlbumId(albumId: Int): Collection<Photo> = dataSource.getPhotosByAlbumId(albumId)
}