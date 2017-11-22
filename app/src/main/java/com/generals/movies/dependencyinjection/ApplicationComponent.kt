package com.generals.movies.dependencyinjection

import com.generals.movies.MovieListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class,
                             ServiceModule::class))
interface ApplicationComponent {

    fun inject(activity: MovieListActivity)
}