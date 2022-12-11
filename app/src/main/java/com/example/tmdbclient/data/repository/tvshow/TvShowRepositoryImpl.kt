package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TVShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domian.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TVShow>? {
        return getTvShowFromCache()
    }

    override suspend fun updateTvShow(): List<TVShow>? {
        val newListOfTvShow = getTvShowFromApi()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowToDB(newListOfTvShow)
        return newListOfTvShow
    }

    suspend fun getTvShowFromApi(): List<TVShow> {
        lateinit var tvShowList: List<TVShow>
        try {
            val response = tvShowRemoteDataSource.getTvShow()
            val body = response.body()
            if (body != null) {
                tvShowList = body.TVShows
            }
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        return tvShowList
    }

    suspend fun getTvShowFromDB(): List<TVShow> {
        lateinit var tvShowList: List<TVShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowFromApi()
            tvShowLocalDataSource.saveTvShowToDB(tvShowList)
        }
        return tvShowList
    }

    suspend fun getTvShowFromCache(): List<TVShow> {
        lateinit var tvShowList: List<TVShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowFromCache()
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowFromDB()
            tvShowCacheDataSource.saveTvShowToCache(tvShowList)
        }
        return tvShowList
    }
}