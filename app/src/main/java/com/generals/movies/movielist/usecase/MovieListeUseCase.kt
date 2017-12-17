package com.generals.movies.movielist.usecase

import com.generals.movies.movielist.model.Movie
import io.reactivex.disposables.Disposable

interface MovieListeUseCase {

    fun loadMovies(onComplete: (List<Movie>) -> Unit,
                   onError: (Throwable) -> Unit): Disposable
}