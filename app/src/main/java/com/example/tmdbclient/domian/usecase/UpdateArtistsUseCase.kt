package com.example.tmdbclient.domian.usecase

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domian.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.updateArtists()
}