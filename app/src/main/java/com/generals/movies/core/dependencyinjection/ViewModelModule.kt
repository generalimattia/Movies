package com.generals.movies.core.dependencyinjection

import com.generals.movies.movielist.databinding.viewmodel.MovieListViewModel
import com.generals.movies.movielist.databinding.viewmodel.MovieListViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class ViewModelModule {

    @Provides
    @Singleton
    fun provideMovieListViewModel(viewModel: MovieListViewModelImpl): MovieListViewModel = viewModel
}