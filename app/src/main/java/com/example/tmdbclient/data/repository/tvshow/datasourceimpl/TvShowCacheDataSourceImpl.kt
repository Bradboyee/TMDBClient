package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import com.example.tmdbclient.data.model.tvshow.TVShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl : TvShowCacheDataSource {
    private var tvShowList = ArrayList<TVShow>()
    override suspend fun getTvShowFromCache(): List<TVShow> {
        return tvShowList
    }

    override suspend fun saveTvShowToCache(tvShows: List<TVShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}