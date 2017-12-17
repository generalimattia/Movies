package com.generals.movies.core.inject

import android.arch.lifecycle.ViewModelProvider
import com.generals.movies.movielist.live.viewmodel.MovieListViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LiveModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(viewModelFactory: MovieListViewModelFactory): ViewModelProvider.Factory = viewModelFactory

}