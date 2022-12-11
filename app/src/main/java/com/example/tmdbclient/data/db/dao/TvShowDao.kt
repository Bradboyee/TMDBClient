package com.example.tmdbclient.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TVShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShow(movies : List<TVShow>)

    @Query("DELETE FROM popular_tvShow")
    suspend fun deleteAllTvShow()

    @Query("SELECT * FROM popular_tvShow")
    suspend fun getTvShows():List<TVShow>
}