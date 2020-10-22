package com.miftah.sifutsal.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

object Tools {

    fun displayImageOriginal(ctx: Context, img: ImageView, @DrawableRes drawable: Int) {
        try {
            Glide.with(ctx).load(drawable)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img)
        } catch (e: Exception) {
        }
    }
}