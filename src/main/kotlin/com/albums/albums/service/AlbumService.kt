package com.albums.albums.service

import com.albums.albums.datasource.AlbumDataSource
import com.albums.albums.model.Album
import org.springframework.stereotype.Service

@Service
class AlbumService (private val dataSource: AlbumDataSource) {
    fun getAlbums(): Collection<Album> = dataSource.getAlbums()
}