package com.generals.movies.movielist.mvp.presenter

import com.generals.movies.movielist.model.Movie
import com.generals.movies.movielist.mvp.view.MovieListView
import com.generals.movies.movielist.usecase.MovieListeUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MovieListPresenterImplTest {

    @Mock
    lateinit var useCase: MovieListeUseCase

    @Mock
    lateinit var view: MovieListView

    private lateinit var sut: MovieListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MovieListPresenterImpl(useCase)
        sut.attachView(view)
    }

    @Test
    fun viewShouldBePresented() {
        val movies = listOf(Movie("Beautiful", "www.strong.it"))

        doAnswer { invocationOnMock ->
            val onComplete: (List<Movie>) -> Unit = invocationOnMock.arguments[0] as (List<Movie>) -> Unit
            onComplete(movies)
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.presentView()

        verify(view).showLoading()
        verify(view).onMovieListLoaded(any())
    }

    @Test
    fun viewShouldShowErrorMessage() {
        doAnswer { invocationOnMock ->
            val onError: (Throwable) -> Unit = invocationOnMock.arguments[1] as (Throwable) -> Unit
            onError(IllegalArgumentException())
            Observable.just(Unit).subscribe()
        }.`when`(useCase).loadMovies(any(), any())

        sut.presentView()

        verify(view).showLoading()
        verify(view).onError(any())
    }
}