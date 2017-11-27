package com.generals.movies

import com.generals.movies.movielist.model.Movie
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ParsingHtmlTest {

    companion object {
        private const val MOVIES_URL = "http://www.mymovies.it/cinema/bologna/"
        private const val MOVIE_ID = "div.scheda_film"
        private const val MOVIE_TITLE_ID = "div.div_titolo_film"
    }

    @Test fun downloadHtml() {
        val document: Document = fetchHtmlDocument()

        assertNotNull(document)

        val movie: Element = document.select(MOVIE_ID).first()
        val movieTitle = movie.select(MOVIE_TITLE_ID).select("a").attr("title")
        assertNotNull(movieTitle)
        assertEquals("Gli sdraiati", movieTitle)

        val movieUrl: String = movie.select("img").select("[alt=$movieTitle]").attr("src")
        assertEquals("http://pad.mymovies.it/filmclub/2017/06/173/locandina.jpg", movieUrl)
    }

    @Test fun downloadMovies() {
        val document: Document = fetchHtmlDocument()

        val movies: Elements = document.select(MOVIE_ID)
        assertEquals(30, movies.size)

        val movieList: List<Movie> = movies.filter {
            extractMovieTitle(it).isNotEmpty()
        }.map {
            val title = extractMovieTitle(it)
            Movie(title, it.select("img").select("[alt=$title]").attr("src"))
        }
        assertEquals(26, movieList.size)
    }

    private fun extractMovieTitle(element: Element) = element.select(MOVIE_TITLE_ID).select("a").attr("title")

    private fun fetchHtmlDocument(): Document {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(MOVIES_URL).build()
        val response: Response = okHttpClient.newCall(request).execute()
        val body = response.body()?.string()
        val document: Document = Jsoup.parse(body)
        return document
    }
}