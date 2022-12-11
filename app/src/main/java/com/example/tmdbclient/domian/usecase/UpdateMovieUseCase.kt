package com.example.tmdbclient.domian.usecase

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domian.repository.MovieRepository

class UpdateMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}