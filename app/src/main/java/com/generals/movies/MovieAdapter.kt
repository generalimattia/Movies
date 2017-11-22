package com.generals.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.generals.movies.model.Movie

import kotlinx.android.synthetic.main.widget_movie_item.view.movie_title

class MovieAdapter(val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.widget_movie_item, parent, false))
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.movie_title.text = movie.title
        }
    }


}