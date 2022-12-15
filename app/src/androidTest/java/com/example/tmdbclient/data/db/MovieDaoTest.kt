package com.example.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.db.dao.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var dao : MovieDao
    private lateinit var database : TMDBDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),TMDBDatabase::class.java).build()
        dao = database.movieDao()
    }

    @Test
    fun saveMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1,"overview1","path1","date1","title1"),
            Movie(2,"overview2","path2","date2","title2"),
            Movie(3,"overview3","path3","date3","title3"),
            Movie(4,"overview4","path4","date4","title4"),
        )
        dao.saveMovie(movies)

        val allMovies = dao.getMovies()
        Truth.assertThat(allMovies).isEqualTo(movies)
    }

    @Test
    fun deleteMovieTest() = runBlocking {
        val movies = listOf(
            Movie(1,"overview1","path1","date1","title1"),
            Movie(2,"overview2","path2","date2","title2"),
            Movie(3,"overview3","path3","date3","title3"),
            Movie(4,"overview4","path4","date4","title4"),
        )
        dao.saveMovie(movies)
        dao.deleteAllMovies()
        val moviesResult = dao.getMovies()
        Truth.assertThat(moviesResult).isEmpty()
    }

    @After
    fun tearDown() {
        database.close()
    }
}