package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import com.example.tmdbclient.data.db.dao.TvShowDao
import com.example.tmdbclient.data.model.tvshow.TVShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TVShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvShowToDB(tvShows: List<TVShow>) {
        return tvShowDao.saveTvShow(tvShows)
    }

    override suspend fun clearAll() {
        return tvShowDao.deleteAllTvShow()
    }
}