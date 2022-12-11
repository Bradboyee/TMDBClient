package com.example.tmdbclient.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movies: List<Movie>)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movie")
    suspend fun getMovies():List<Movie>
}