package com.generals.movies.movielist.mvp.view

import com.generals.movies.movielist.mvp.model.Movie

interface MovieListView {

    fun showLoading()

    fun onMovieListLoaded(movieList: List<Movie>)

    fun onError(e: Throwable)
}