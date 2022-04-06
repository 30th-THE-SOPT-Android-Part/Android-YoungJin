package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInfo(
    val id: String,
    val password: String,
) : Parcelable