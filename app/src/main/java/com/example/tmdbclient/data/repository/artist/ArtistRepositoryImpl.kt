package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domian.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource,
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getMovieFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtist = getArtistFromApi()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtist)
        return newListOfArtist
    }

    suspend fun getArtistFromApi(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtist()
            val body = response.body()
            if (body != null) {
                artistList = body.artists
            }
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        return artistList
    }

    suspend fun getMovieFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistFromDB()
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistFromApi()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return artistList
    }

    suspend fun getMovieFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistFormCache()
        } catch (exception: Exception) {
            Log.i("MY TAG", exception.message.toString())
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getMovieFromDB()
            artistCacheDataSource.saveArtistToCache(artistList)
        }
        return artistList
    }
}