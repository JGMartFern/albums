package com.albums.albums.service

import com.albums.albums.datasource.AlbumDataSource
import com.albums.albums.datasource.PhotoDataSource
import com.albums.albums.model.Album
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AlbumService(@Qualifier("websiteAlbum") private val albumDataSource: AlbumDataSource, @Qualifier("websitePhoto") private val photoDataSource: PhotoDataSource) {
    fun getAlbums(): Collection<Album> {
        val albums = albumDataSource.getAlbums()
        val photos = photoDataSource.getPhotos()

        return albums.map { album ->
            val albumPhotos = photos.filter { it.albumId == album.id }
            album.copy(photos = albumPhotos)
        }
    }
}
