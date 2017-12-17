package com.generals.movies.movielist.live.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.generals.movies.MovieApplication
import com.generals.movies.R
import com.generals.movies.movielist.live.viewmodel.MovieListViewModel
import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.mvvm.view.MovieAdapterMVVM
import kotlinx.android.synthetic.main.activity_movie_list_live.*
import javax.inject.Inject

class MovieListActivityLive : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_live)

        MovieApplication.getApplicationComponent(this).inject(this)
        movie_list.layoutManager = GridLayoutManager(this@MovieListActivityLive, 2)

        val viewModel = ViewModelProviders.of(this,
                                              viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.loadMovies()

        viewModel.liveMovies.observe(this, Observer { movies: List<Movie>? ->
            movies?.let {
                movie_list_loader.visibility = View.GONE
                movie_list.adapter = MovieAdapterMVVM(movies)
            }
        })

        viewModel.liveErrorMessage.observe(this, Observer { _: String? ->
            movie_list_loader.visibility = View.GONE
            Toast.makeText(this@MovieListActivityLive,
                           "Error while downloading liveMovies",
                           Toast.LENGTH_LONG).show()
        })
    }
}