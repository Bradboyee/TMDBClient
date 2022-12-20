package com.example.tmdbclient.presentation.di.core

import android.app.Application
import androidx.room.Room
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.dao.ArtistDao
import com.example.tmdbclient.data.db.dao.MovieDao
import com.example.tmdbclient.data.db.dao.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieDataBase(application: Application): TMDBDatabase {
        return Room.databaseBuilder(application, TMDBDatabase::class.java, "tmdb_client").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {
        return tmdbDatabase.artistDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDao {
        return tmdbDatabase.tvShowDao()
    }
}