package com.generals.movies.core.dependencyinjection

import com.generals.movies.movielist.databinding.view.MovieListActivityDataBinding
import com.generals.movies.movielist.mvp.view.MovieListActivityMVP
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class,
                             PresenterModule::class,
                             ViewModelModule::class))
interface ApplicationComponent {

    fun inject(activity: MovieListActivityMVP)

    fun inject(activity: MovieListActivityDataBinding)
}