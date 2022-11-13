package com.gricsan.caradaro.base.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(state: Int = View.GONE) {
    this.visibility = state
}

fun ImageView.loadImageFromUrl(
    url: String,
    @DrawableRes
    thumbnailResId: Int = 0,
    options: RequestOptions = RequestOptions()
) {
    val thumbnail = Glide.with(context)
        .load(thumbnailResId)
        .apply(options)

    Glide.with(context)
        .load(url)
        .thumbnail(thumbnail)
        .apply(options)
        .into(this)
}