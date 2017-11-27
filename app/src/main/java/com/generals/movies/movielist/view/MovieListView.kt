package com.generals.movies.movielist.view

import com.generals.movies.movielist.model.Movie

interface MovieListView {

    fun onMovieListLoaded(movieList: List<Movie>)

    fun onError(e: Throwable)
}