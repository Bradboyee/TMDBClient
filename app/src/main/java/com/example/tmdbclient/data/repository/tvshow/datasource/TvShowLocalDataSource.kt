package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tvshow.TVShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB(): List<TVShow>
    suspend fun saveTvShowToDB(tvShows: List<TVShow>)
    suspend fun clearAll()
}