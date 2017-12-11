package com.generals.movies.movielist.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.generals.movies.movielist.databinding.view.MovieAdapterDataBinding
import com.generals.movies.movielist.mvp.model.Movie

@SuppressWarnings("unchecked")
@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView,
             items: List<Movie>) {
    recyclerView.adapter = MovieAdapterDataBinding(items)
}