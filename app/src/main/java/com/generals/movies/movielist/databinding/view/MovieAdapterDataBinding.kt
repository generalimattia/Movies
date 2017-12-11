package com.generals.movies.movielist.databinding.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.generals.movies.databinding.WidgetMovieItemDataBindingBinding
import com.generals.movies.movielist.mvp.model.Movie

class MovieAdapterDataBinding(val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapterDataBinding.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(WidgetMovieItemDataBindingBinding.inflate(layoutInflater, parent, false))
    }

    class MovieViewHolder(private val binding: WidgetMovieItemDataBindingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}