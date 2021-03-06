package com.generals.movies.movielist.mvvm.viewmodel

import android.databinding.*
import com.generals.movies.movielist.model.Movie

interface MovieListViewModel : Observable {

    @Bindable fun getItems(): ObservableArrayList<Movie>

    @Bindable fun getProgressBarVisibility(): ObservableInt

    fun bindView()

    fun onDestroy()
}