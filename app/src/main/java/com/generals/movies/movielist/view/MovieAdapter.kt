package com.generals.movies.movielist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.generals.movies.R
import com.generals.movies.movielist.model.Movie
import kotlinx.android.synthetic.main.widget_movie_item.view.*

class MovieAdapter(val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieAdapter.MovieViewHolder(layoutInflater.inflate(R.layout.widget_movie_item,
                                                                   parent,
                                                                   false))
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                    .load(movie.imageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(itemView.movie_image)
        }
    }


}