package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.FakeMovieRepository
import com.example.tmdbclient.domian.usecase.GetMoviesUseCase
import com.example.tmdbclient.domian.usecase.UpdateMovieUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
      val fakeMovieRepository = FakeMovieRepository()
      val getMovieUseCase = GetMoviesUseCase(fakeMovieRepository)
      val updateMovieUseCase = UpdateMovieUseCase(fakeMovieRepository)
      viewModel = MovieViewModel(getMovieUseCase,updateMovieUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "path1", "date1", "title1"))
        movies.add(Movie(2, "overview2", "path2", "date2", "title2"))

        val currentList = viewModel.getMovies().getOrAwaitValue()
        assertThat(currentList).isEqualTo(movies)

    }

    @Test
    fun updateMovies_returnsUpdatedList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(3, "overview3", "path3", "date3", "title3"))
        movies.add(Movie(4, "overview4", "path4", "date4", "title4"))

        val updatedList = viewModel.updateMovies().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(movies)

    }









}









