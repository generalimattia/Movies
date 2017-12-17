package com.generals.movies.core.inject

import com.generals.movies.movielist.usecase.MovieListUseCaseImpl
import com.generals.movies.movielist.usecase.MovieListeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieListUseCase(useCase: MovieListUseCaseImpl): MovieListeUseCase = useCase
}