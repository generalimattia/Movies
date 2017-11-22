package com.generals.movies.services

import com.generals.movies.model.Movie

interface MovieService {

    fun fetchMovies(onNext: (List<Movie>) -> Unit,
                    onError: (Throwable) -> Unit)
}