package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domian.usecase.GetTvShowUseCase
import com.example.tmdbclient.domian.usecase.UpdateTvShowUseCase
import com.example.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @Provides
    @TvShowScope
    fun provideTvShowViewModelFactory(
        getTvShowUseCase: GetTvShowUseCase,
        updateTvShowUseCase: UpdateTvShowUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowUseCase, updateTvShowUseCase)
    }
}