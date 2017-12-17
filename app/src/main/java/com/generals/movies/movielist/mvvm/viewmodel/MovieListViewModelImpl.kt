package com.generals.movies.movielist.mvvm.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.view.View
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.generals.movies.movielist.model.Movie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieListViewModelImpl @Inject constructor(private val useCase: MovieListeUseCase) : MovieListViewModel, BaseObservable() {

    private var moviesDisposable: Disposable? = null

    private var _observableMovies: ObservableArrayList<Movie> = ObservableArrayList()
    private var _observableProgressBarVisibility: ObservableInt = ObservableInt()

    override fun bindView() {
        moviesDisposable = useCase.loadMovies({ it: List<Movie> ->
                                                  _observableProgressBarVisibility.set(View.GONE)
                                                  _observableMovies.addAll(it)
                                              }) { _: Throwable -> _observableProgressBarVisibility.set(View.GONE) }

    }

    override fun getItems(): ObservableArrayList<Movie> = _observableMovies
    override fun getProgressBarVisibility(): ObservableInt = _observableProgressBarVisibility

    override fun onDestroy() {
        moviesDisposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }


}