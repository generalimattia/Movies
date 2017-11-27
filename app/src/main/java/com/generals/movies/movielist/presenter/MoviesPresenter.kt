package com.generals.movies.movielist.presenter

import com.generals.movies.core.mvp.Presenter
import com.generals.movies.movielist.view.MovieListView

interface MoviesPresenter : Presenter<MovieListView> {

    fun presentView()

    fun onDestroy()
}