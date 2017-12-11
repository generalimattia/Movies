package com.generals.movies.movielist.databinding.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.generals.movies.MovieApplication
import com.generals.movies.R
import com.generals.movies.databinding.ActivityMovieListDataBindingBinding
import com.generals.movies.movielist.databinding.viewmodel.MovieListViewModel
import javax.inject.Inject

class MovieListActivityDataBinding : AppCompatActivity() {

    @Inject lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MovieApplication.getApplicationComponent(this).inject(this)

        val binding: ActivityMovieListDataBindingBinding = DataBindingUtil.setContentView(this,
                                                                                          R.layout.activity_movie_list_data_binding)
        binding.viewModel = viewModel
        binding.movieList.layoutManager = GridLayoutManager(this, 2)

        viewModel.bindView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
