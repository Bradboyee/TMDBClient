package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domian.usecase.GetTvShowUseCase
import com.example.tmdbclient.domian.usecase.UpdateTvShowUseCase

class TvShowViewModel(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModel() {
    fun getTvShow() = liveData {
        val tvShowList = getTvShowUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShow() = liveData {
        val tvShowList = updateTvShowUseCase.execute()
        emit(tvShowList)
    }
}