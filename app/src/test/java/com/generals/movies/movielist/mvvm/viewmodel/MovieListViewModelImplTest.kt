package com.generals.movies.movielist.mvvm.viewmodel

import android.view.View
import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieListViewModelImplTest {

    @Mock
    lateinit var useCase: MovieListeUseCase

    private lateinit var sut: MovieListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MovieListViewModelImpl(useCase)
    }

    @Test
    fun viewShouldBeBound() {
        val movies = listOf(Movie("Beautiful", "www.strong.it"))

        doAnswer { invocationOnMock ->
            val onComplete: (List<Movie>) -> Unit = invocationOnMock.arguments[0] as (List<Movie>) -> Unit
            onComplete(movies)
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.bindView()

        assertEquals(movies.size, sut.getItems().size)
        assertEquals(View.GONE, sut.getProgressBarVisibility().get())
    }

    @Test
    fun viewShouldShowErrorMessage() {
        doAnswer { invocationOnMock ->
            val onError: (Throwable) -> Unit = invocationOnMock.arguments[1] as (Throwable) -> Unit
            onError(IllegalArgumentException())
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.bindView()

        assertEquals(0, sut.getItems().size)
        assertEquals(View.GONE, sut.getProgressBarVisibility().get())
    }

}