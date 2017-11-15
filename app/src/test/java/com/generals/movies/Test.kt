package com.generals.movies

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

class Test {

    companion object {
        private const val MOVIES_URL = "http://www.mymovies.it/cinema/bologna/"
        private const val MOVIE_ID = "div.scheda_film"
        private const val MOVIE_TITLE_ID = "div.div_titolo_film"
    }

    @Test
    fun downloadHtml() {
        val document: Document = fetchHtmlDocument()

        assertNotNull(document)

        val movie: Element = document.select(MOVIE_ID)
                .first()
        val movieTitle = movie.select(MOVIE_TITLE_ID).first().select("a").attr("title")
        assertNotNull(movieTitle)
        assertEquals("Paddington 2", movieTitle)

        val movieUrl: String = movie.select("img").select("[alt=$movieTitle]").attr("src")
        assertEquals("http://pad.mymovies.it/filmclub/2016/10/088/locandina.jpg", movieUrl)
    }

    @Test
    fun downloadMovies() {
        val document: Document = fetchHtmlDocument()

        val movies: Elements = document.select(MOVIE_ID)
        assertEquals(32, movies.size)
    }

    private fun fetchHtmlDocument(): Document {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(MOVIES_URL).build()
        val response: Response = okHttpClient.newCall(request).execute()
        val body = response.body()?.string()
        val document: Document = Jsoup.parse(body)
        return document
    }
}