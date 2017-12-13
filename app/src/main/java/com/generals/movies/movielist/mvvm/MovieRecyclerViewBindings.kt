package com.generals.movies.movielist.mvvm

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.generals.movies.movielist.mvvm.view.MovieAdapterMVVM
import com.generals.movies.movielist.model.Movie

@SuppressWarnings("unchecked")
@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView,
             items: List<Movie>) {
    recyclerView.adapter = MovieAdapterMVVM(items)
}