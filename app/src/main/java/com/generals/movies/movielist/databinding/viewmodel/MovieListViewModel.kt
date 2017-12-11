package com.generals.movies.movielist.databinding.viewmodel

import android.databinding.*
import com.generals.movies.movielist.mvp.model.Movie

interface MovieListViewModel : Observable {

    @Bindable fun getItems(): ObservableArrayList<Movie>

    @Bindable fun getProgressBarVisibility(): ObservableInt

    fun bindView()

    fun onDestroy()
}