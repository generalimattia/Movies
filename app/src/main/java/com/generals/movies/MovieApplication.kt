package com.generals.movies

import android.app.Application
import android.content.Context
import com.generals.movies.core.dependencyinjection.ApplicationComponent
import com.generals.movies.core.dependencyinjection.DaggerApplicationComponent

class MovieApplication : Application() {

    lateinit var component: ApplicationComponent

    companion object {

        fun getApplicationComponent(context: Context): ApplicationComponent = (context.applicationContext as MovieApplication).component
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().build()
    }
}