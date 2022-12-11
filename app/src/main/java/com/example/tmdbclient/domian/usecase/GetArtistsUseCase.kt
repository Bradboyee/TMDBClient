package com.example.tmdbclient.domian.usecase

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domian.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}