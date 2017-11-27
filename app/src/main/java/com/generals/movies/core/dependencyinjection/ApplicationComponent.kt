package com.generals.movies.core.dependencyinjection

import com.generals.movies.movielist.view.MovieListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class,
                             ServiceModule::class))
interface ApplicationComponent {

    fun inject(activity: MovieListActivity)
}