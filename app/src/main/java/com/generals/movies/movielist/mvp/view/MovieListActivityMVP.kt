package com.generals.movies.movielist.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.generals.movies.MovieApplication
import com.generals.movies.R
import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.mvp.presenter.MovieListPresenter
import kotlinx.android.synthetic.main.activity_movie_list_mvp.*
import javax.inject.Inject

class MovieListActivityMVP : AppCompatActivity(), MovieListView {

    @Inject lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_mvp)

        MovieApplication.getApplicationComponent(this).inject(this)

        presenter.attachView(this)
        presenter.presentView()

    }

    override fun showLoading() {
        movie_list_loader.visibility = View.VISIBLE
    }

    override fun onMovieListLoaded(movieList: List<Movie>) {
        movie_list_loader.visibility = View.GONE
        movie_list.layoutManager = GridLayoutManager(this@MovieListActivityMVP, 2)
        movie_list.adapter = MovieAdapterMVP(movieList)
    }

    override fun onError(e: Throwable) {
        movie_list_loader.visibility = View.GONE
        Toast.makeText(this@MovieListActivityMVP,
                       "Error while downloading movies",
                       Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
