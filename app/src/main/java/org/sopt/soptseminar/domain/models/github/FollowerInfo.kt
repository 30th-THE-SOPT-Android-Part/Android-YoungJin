package org.sopt.soptseminar.domain.models.github

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FollowerInfo(
    val name: String,
    val profile: String,
    val url: String,
    val description: String? = null,
    val id: Int
) : Parcelable