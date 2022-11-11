package com.gricsan.caradaro.base.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(state: Int = View.GONE) {
    this.visibility = state
}

fun ImageView.loadImageFromUrl(
    url: String,
    @DrawableRes
    placeholderResId: Int = 0
) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(placeholderResId)
        .into(this)
}