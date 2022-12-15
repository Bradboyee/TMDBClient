package com.example.tmdbclient.presentation.artist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.FakeArtistRepository
import com.example.tmdbclient.domian.usecase.GetArtistsUseCase
import com.example.tmdbclient.domian.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtistViewModelTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ArtistViewModel
    @Before
    fun setUp() {
        val fakeArtistRepository = FakeArtistRepository()
        val getArtistsUseCase = GetArtistsUseCase(fakeArtistRepository)
        val updateArtistsUseCase = UpdateArtistsUseCase(fakeArtistRepository)
        viewModel = ArtistViewModel(getArtistsUseCase,updateArtistsUseCase)
    }

    @Test
    fun getArtist_returnCurrentList() {
        val fakeArtistList = mutableListOf<Artist>()
        fakeArtistList.add(Artist(1,"name1",1.0,"path1"))
        fakeArtistList.add(Artist(2,"name2",2.0,"path2"))

        val currentList = viewModel.getArtist().getOrAwaitValue()
        assertThat(currentList).isEqualTo(fakeArtistList)
    }
    @Test
    fun updateArtist_returnUpdatedList() {
        val fakeArtistList = mutableListOf<Artist>()
        fakeArtistList.add(Artist(3,"name3",3.0,"path3"))
        fakeArtistList.add(Artist(4,"name4",4.0,"path4"))

        val updatedList = viewModel.updateArtist().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(fakeArtistList)

    }
}