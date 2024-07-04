package com.albums.albums.datasource.mock

import com.albums.albums.datasource.AlbumDataSource
import com.albums.albums.model.Album
import org.springframework.stereotype.Repository

@Repository
class MockAlbumDataSource : AlbumDataSource {

    val albums = listOf(
        Album(1, 1, "John"),
        Album(10, 11, "James"),
        Album(2, 9, "Jimmy")
    )

    override fun getAlbums(): Collection<Album> = albums
}
