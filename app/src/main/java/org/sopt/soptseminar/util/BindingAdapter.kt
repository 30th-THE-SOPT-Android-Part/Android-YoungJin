package org.sopt.soptseminar.util

import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("app:imageRes")
fun ImageView.setImage(@DrawableRes imageResId: Int) {
    Glide.with(this).load(imageResId).into(this)
}

@BindingAdapter("app:imageUri")
fun ImageView.setImage(imageUri: String?) {
    if (imageUri == null) return
    Glide.with(this).load(imageUri).into(this)
}

@BindingAdapter(value = ["dividerHeight", "dividerPadding", "dividerColor"], requireAll = false)
fun RecyclerView.setDivider(
    dividerHeight: Float?,
    dividerPadding: Float?,
    @ColorInt dividerColor: Int?
) {
    val decoration = CustomDecoration(
        height = dividerHeight ?: 0f,
        padding = dividerPadding ?: 0f,
        color = dividerColor ?: Color.TRANSPARENT
    )
    addItemDecoration(decoration)
}