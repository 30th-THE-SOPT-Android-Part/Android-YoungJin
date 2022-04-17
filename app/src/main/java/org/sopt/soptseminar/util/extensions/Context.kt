package org.sopt.soptseminar.util.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String, isShort: Boolean = true) {
    val toastLength = if (isShort) {
        Toast.LENGTH_SHORT
    } else {
        Toast.LENGTH_LONG
    }
    Toast.makeText(this, message, toastLength).show()
}