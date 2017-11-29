package com.generals.movies.movielist.databinding.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.generals.movies.R
import com.generals.movies.databinding.ActivityMovieList2Binding

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieList2Binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list2)
        binding.movieList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
