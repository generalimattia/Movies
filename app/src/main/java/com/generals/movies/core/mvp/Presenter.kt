package com.generals.movies.core.mvp

interface Presenter<T> {

    fun attachView(view: T)

    fun ifIsViewAttached(action: T.() -> Unit)
}