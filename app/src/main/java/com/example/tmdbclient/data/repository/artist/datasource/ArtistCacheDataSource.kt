package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistFormCache():List<Artist>
    suspend fun saveArtistToCache(artists: List<Artist>)
}