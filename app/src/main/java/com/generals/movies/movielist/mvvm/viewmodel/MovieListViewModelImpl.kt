package com.generals.movies.movielist.mvvm.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.view.View
import com.generals.movies.movielist.model.Movie
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class MovieListViewModelImpl @Inject constructor(private val okHttpClient: OkHttpClient) : MovieListViewModel, BaseObservable() {

    companion object {
        private const val MOVIES_URL = "http://www.mymovies.it/cinema/bologna/"
        private const val MOVIE_ID = "div.scheda_film"
        private const val MOVIE_TITLE_ID = "div.div_titolo_film"
    }

    private var moviesDisposable: Disposable? = null

    private var _observableMovies: ObservableArrayList<Movie> = ObservableArrayList()
    private var _observableProgressBarVisibility: ObservableInt = ObservableInt()

    override fun bindView() {
        moviesDisposable = Observable.create { emitter: ObservableEmitter<List<Movie>> ->
            try {
                _observableMovies.clear()
                val request = Request.Builder().url(MOVIES_URL).build()
                val response: Response = okHttpClient.newCall(request).execute()
                val body = response.body()?.string()
                val document: Document = Jsoup.parse(body)
                val movies: Elements = document.select(MOVIE_ID)

                val movieList = movies.filter {
                    extractMovieTitle(it).isNotEmpty()
                }.map {
                    val title = extractMovieTitle(it)
                    try {
                        Movie(title, it.select("img").select("[alt=$title]").attr("src"))
                    } catch (e: Exception) {
                        Movie(title, null)
                    }
                }
                emitter.onNext(movieList)
                emitter.onComplete()
            } catch (e: Exception) {
                e.printStackTrace()
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ it: List<Movie> ->
                                                                                               _observableProgressBarVisibility.set(View.GONE)
                                                                                               _observableMovies.addAll(it)
                                                                                           }) { it: Throwable? -> it?.let {
            _observableProgressBarVisibility.set(View.GONE) }}

    }

    override fun getItems(): ObservableArrayList<Movie> = _observableMovies
    override fun getProgressBarVisibility(): ObservableInt = _observableProgressBarVisibility

    private fun extractMovieTitle(element: Element) = element.select(MOVIE_TITLE_ID).select("a").attr(
            "title")

    override fun onDestroy() {
        moviesDisposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }


}