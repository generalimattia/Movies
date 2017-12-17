package com.generals.movies.movielist.live.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.generals.movies.movielist.model.Movie
import io.reactivex.disposables.Disposable

class MovieListViewModel(private val useCase: MovieListeUseCase) : ViewModel() {

    private var moviesDisposable: Disposable? = null

    var liveMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    var liveErrorMessage: MutableLiveData<String> = MutableLiveData()

    fun loadMovies() {
        moviesDisposable = useCase.loadMovies({ it: List<Movie> -> liveMovies.value = it }) { throwable: Throwable -> liveErrorMessage.value = throwable.message }
    }

    override fun onCleared() {
        super.onCleared()
        moviesDisposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

}