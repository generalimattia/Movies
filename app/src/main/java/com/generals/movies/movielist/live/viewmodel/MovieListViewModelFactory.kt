package com.generals.movies.movielist.live.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.generals.movies.movielist.usecase.MovieListeUseCase
import javax.inject.Inject

class MovieListViewModelFactory @Inject constructor(private val useCase: MovieListeUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}