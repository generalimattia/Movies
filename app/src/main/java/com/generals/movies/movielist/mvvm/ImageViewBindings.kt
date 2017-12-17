package com.generals.movies.movielist.mvvm

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

@SuppressWarnings("unchecked")
@BindingAdapter("url", "loader", requireAll = true)
fun loadImage(imageView: ImageView,
              url: String?,
              loader: ProgressBar) {
    url?.let {
        loader.visibility = View.VISIBLE
        Glide.with(imageView.context)
                .load(it)
                .listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        loader.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        loader.visibility = View.GONE
                        return false
                    }
                })
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
    }
}
