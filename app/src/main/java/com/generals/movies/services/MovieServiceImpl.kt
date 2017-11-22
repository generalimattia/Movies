package com.generals.movies.services

import com.generals.movies.model.Movie
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class MovieServiceImpl @Inject constructor(private val okHttpClient: OkHttpClient) : MovieService {

    companion object {
        private const val MOVIES_URL = "http://www.mymovies.it/cinema/bologna/"
        private const val MOVIE_ID = "div.scheda_film"
        private const val MOVIE_TITLE_ID = "div.div_titolo_film"
    }

    override fun fetchMovies(onNext: (List<Movie>) -> Unit, onError: (Throwable) -> Unit) {

        val observable: Observable<List<Movie>> = Observable.create { e: ObservableEmitter<List<Movie>> ->

            try {
                val request = Request.Builder().url(MOVIES_URL).build()
                val response: Response = okHttpClient.newCall(request).execute()
                val body = response.body()?.string()
                val document: Document = Jsoup.parse(body)
                val movies: Elements = document.select(MOVIE_ID)

                val movieList = movies.map {
                    val title = it.select(MOVIE_TITLE_ID).first().select("a").attr("title")
                    Movie(title, it.select("img").select("[alt=$title]").attr("src"))
                }
                e.onNext(movieList)
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e)
            }
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError)

    }
}