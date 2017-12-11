package com.generals.movies.core.dependencyinjection

import com.generals.movies.movielist.mvp.presenter.MovieListPresenter
import com.generals.movies.movielist.mvp.presenter.MovieListPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideMovieService(presenter: MovieListPresenterImpl): MovieListPresenter = presenter
}