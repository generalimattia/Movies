package com.generals.movies.movielist.mvp.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(val title: String, val imageUrl: String?) : Parcelable {

    constructor(source: Parcel) : this(source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(imageUrl)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}