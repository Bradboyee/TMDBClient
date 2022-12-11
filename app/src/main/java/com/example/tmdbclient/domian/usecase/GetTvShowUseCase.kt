package com.example.tmdbclient.domian.usecase

import com.example.tmdbclient.data.model.tvshow.TVShow
import com.example.tmdbclient.domian.repository.TvShowRepository

class GetTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TVShow>? = tvShowRepository.getTvShows()
}