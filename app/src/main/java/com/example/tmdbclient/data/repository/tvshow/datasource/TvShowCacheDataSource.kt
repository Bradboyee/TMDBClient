package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tvshow.TVShow

interface TvShowCacheDataSource {
    suspend fun getTvShowFromCache(): List<TVShow>
    suspend fun saveTvShowToCache(tvShows: List<TVShow>)
}