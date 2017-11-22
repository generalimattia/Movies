package com.generals.movies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.generals.movies.model.Movie
import com.generals.movies.services.MovieService
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var service: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        MovieApplication.getApplicationComponent(this).inject(this)

        service.fetchMovies({movies: List<Movie> ->
                                movie_list.layoutManager = LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.VERTICAL, false)
                                movie_list.adapter = MovieAdapter(movies)
                            }) { Toast.makeText(this@MovieListActivity, "Error while downloading movies", Toast.LENGTH_LONG)}
    }
}
