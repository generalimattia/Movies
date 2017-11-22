package com.generals.movies.dependencyinjection

import com.generals.movies.services.MovieService
import com.generals.movies.services.MovieServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideMovieService(service: MovieServiceImpl): MovieService = service
}