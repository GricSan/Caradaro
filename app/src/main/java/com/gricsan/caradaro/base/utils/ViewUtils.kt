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
    placeholderResId: Int = 0,
    options: RequestOptions = RequestOptions()
) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholderResId)
        .apply(options)
        .into(this)
}