package com.example.tmdbclient.domian.repository

import com.example.tmdbclient.data.model.tvshow.TVShow

interface TvShowRepository {
    suspend fun getTvShows():List<TVShow>?
    suspend fun updateTvShow():List<TVShow>?
}