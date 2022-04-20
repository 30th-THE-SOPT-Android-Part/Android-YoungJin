package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FollowerInfo(
    val id: Int,
    val name: String,
    val profile: String,
    val description: String? = null,
    val url: String,
): Parcelable