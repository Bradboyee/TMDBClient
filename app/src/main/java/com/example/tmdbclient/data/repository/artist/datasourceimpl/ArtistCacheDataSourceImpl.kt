package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()
    override suspend fun getArtistFormCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}