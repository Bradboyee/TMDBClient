package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.domian.usecase.GetMoviesUseCase
import com.example.tmdbclient.domian.usecase.UpdateMovieUseCase
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    @MovieScope
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMovieUseCase: UpdateMovieUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase, updateMovieUseCase)
    }
}