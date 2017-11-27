package com.generals.movies.core.mvp

import java.lang.ref.WeakReference

abstract class BasePresenter<T> : Presenter<T> {

    private var weakView: WeakReference<T>? = null

    override fun attachView(view: T) {
        weakView = WeakReference(view)
    }

    override fun ifIsViewAttached(action: T.() -> Unit) {
        weakView?.get()?.action()
    }
}