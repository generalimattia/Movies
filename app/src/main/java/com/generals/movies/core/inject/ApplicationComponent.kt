package com.generals.movies.core.inject

import com.generals.movies.movielist.live.view.MovieListActivityLive
import com.generals.movies.movielist.mvp.view.MovieListActivityMVP
import com.generals.movies.movielist.mvvm.view.MovieListActivityMVVM
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class,
                             UseCaseModule::class,
                             PresenterModule::class,
                             ViewModelModule::class,
                             LiveModule::class))
interface ApplicationComponent {

    fun inject(activity: MovieListActivityMVP)

    fun inject(activity: MovieListActivityMVVM)

    fun inject(activity: MovieListActivityLive)
}