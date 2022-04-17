package org.sopt.soptseminar.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageRes")
fun ImageView.setImage(@DrawableRes imageResId: Int) {
    Glide.with(this).load(imageResId).into(this)
}