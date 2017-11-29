package com.generals.movies.movielist.mvp.presenter

import com.generals.movies.core.mvp.Presenter
import com.generals.movies.movielist.mvp.view.MovieListView

interface MoviesPresenter : Presenter<MovieListView> {

    fun presentView()

    fun onDestroy()
}