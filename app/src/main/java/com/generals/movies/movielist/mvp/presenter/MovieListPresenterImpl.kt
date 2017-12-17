package com.generals.movies.movielist.mvp.presenter

import com.generals.movies.core.mvp.BasePresenter
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.mvp.view.MovieListView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieListPresenterImpl @Inject constructor(private val useCase: MovieListeUseCase) : MovieListPresenter, BasePresenter<MovieListView>() {

    private var moviesDisposable: Disposable? = null

    override fun presentView() {
        ifIsViewAttached { showLoading() }
        moviesDisposable = useCase.loadMovies({ it: List<Movie> ->
                                                  ifIsViewAttached { onMovieListLoaded(it) }
                                              }) { it: Throwable? ->
            it?.let { ifIsViewAttached { onError(it) } }
        }
    }

    override fun onDestroy() {
        moviesDisposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }
}