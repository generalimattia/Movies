package com.generals.movies.movielist.mvp.view

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.generals.movies.R
import com.generals.movies.movielist.mvp.model.Movie
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

            movie.imageUrl?.let {
                itemView.image_loader.visibility = View.VISIBLE
                itemView.movie_image.contentDescription = movie.title

                Glide.with(itemView.context).load(movie.imageUrl).listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        itemView.image_loader.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        itemView.image_loader.visibility = View.GONE
                        return false
                    }
                }).apply(RequestOptions.centerCropTransform())
                        .into(itemView.movie_image)
            }
        }
    }


}