package com.generals.movies.movielist.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.generals.movies.databinding.WidgetMovieItemMvvmBinding
import com.generals.movies.movielist.mvp.model.Movie

class MovieAdapterMVVM(val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapterMVVM.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(WidgetMovieItemMvvmBinding.inflate(layoutInflater, parent, false))
    }

    class MovieViewHolder(private val binding: WidgetMovieItemMvvmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}