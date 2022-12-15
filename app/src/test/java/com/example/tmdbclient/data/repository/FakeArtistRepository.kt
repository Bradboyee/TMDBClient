package com.example.tmdbclient.data.repository

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domian.repository.ArtistRepository

class FakeArtistRepository : ArtistRepository {
    private val fakeArtistList = mutableListOf<Artist>()
    init {
        fakeArtistList.add(Artist(1,"name1",1.0,"path1"))
        fakeArtistList.add(Artist(2,"name2",2.0,"path2"))
    }
    override suspend fun getArtists(): List<Artist>? {
        return fakeArtistList
    }

    override suspend fun updateArtists(): List<Artist>? {
        fakeArtistList.clear()
        fakeArtistList.add(Artist(3,"name3",3.0,"path3"))
        fakeArtistList.add(Artist(4,"name4",4.0,"path4"))
        return fakeArtistList
    }
}