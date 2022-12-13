package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domian.usecase.GetArtistsUseCase
import com.example.tmdbclient.domian.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @Provides
    @ArtistScope
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
    }
}