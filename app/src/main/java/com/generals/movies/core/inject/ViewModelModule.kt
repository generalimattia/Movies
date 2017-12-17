package com.generals.movies.core.inject

import com.generals.movies.movielist.mvvm.viewmodel.MovieListViewModel
import com.generals.movies.movielist.mvvm.viewmodel.MovieListViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class ViewModelModule {

    @Provides
    @Singleton
    fun provideMovieListViewModel(viewModel: MovieListViewModelImpl): MovieListViewModel = viewModel
}