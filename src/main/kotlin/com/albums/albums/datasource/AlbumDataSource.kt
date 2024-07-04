package com.albums.albums.datasource

import com.albums.albums.model.Album

interface AlbumDataSource {

    fun getAlbums(): Collection<Album>
}
