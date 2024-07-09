package com.albums.albums.service

import com.albums.albums.datasource.PhotoDataSource
import com.albums.albums.model.Photo
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class PhotoService(@Qualifier("websitePhoto") private val dataSource: PhotoDataSource) {
    fun getPhotos(): Collection<Photo> = dataSource.getPhotos()
    fun getPhotosByAlbumId(albumId: Int): Collection<Photo> = dataSource.getPhotosByAlbumId(albumId)
}
