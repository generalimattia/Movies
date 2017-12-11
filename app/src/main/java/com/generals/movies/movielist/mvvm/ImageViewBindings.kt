package com.generals.movies.movielist.mvvm

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressWarnings("unchecked")
@BindingAdapter("url")
fun loadImage(imageView: ImageView,
              url: String?) {
    url?.let {
        Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
    }
}
