package com.generals.movies.movielist.live.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieListViewModelTest {

    @Mock
    lateinit var useCase: MovieListeUseCase

    private lateinit var sut: MovieListViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MovieListViewModel(useCase)
    }

    @Test
    fun viewShouldBeBound() {
        val movies = listOf(Movie("Beautiful", "www.strong.it"))
        val moviesObserver: Observer<List<Movie>> = mock()
        val errorMessageObserver: Observer<String> = mock()

        doAnswer { invocationOnMock ->
            val onComplete: (List<Movie>) -> Unit = invocationOnMock.arguments[0] as (List<Movie>) -> Unit
            onComplete(movies)
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.liveMovies.observeForever(moviesObserver)
        sut.liveErrorMessage.observeForever(errorMessageObserver)

        sut.loadMovies()

        verify(moviesObserver).onChanged(movies)
        verify(errorMessageObserver, never()).onChanged(any())
    }

    @Test
    fun viewShouldShowErrorMessage() {
        val moviesObserver: Observer<List<Movie>> = mock()
        val errorMessageObserver: Observer<String> = mock()

        doAnswer { invocationOnMock ->
            val onError: (Throwable) -> Unit = invocationOnMock.arguments[1] as (Throwable) -> Unit
            onError(IllegalArgumentException("Expected error"))
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.liveMovies.observeForever(moviesObserver)
        sut.liveErrorMessage.observeForever(errorMessageObserver)

        sut.loadMovies()

        verify(moviesObserver, never()).onChanged(any())
        verify(errorMessageObserver).onChanged("Expected error")
    }

}