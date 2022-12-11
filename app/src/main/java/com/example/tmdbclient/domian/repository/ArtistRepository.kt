package com.example.tmdbclient.domian.repository

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}