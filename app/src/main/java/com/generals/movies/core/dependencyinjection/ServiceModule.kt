package com.generals.movies.core.dependencyinjection

import com.generals.movies.movielist.presenter.MoviesPresenter
import com.generals.movies.movielist.presenter.MoviesPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideMovieService(service: MoviesPresenterImpl): MoviesPresenter = service
}