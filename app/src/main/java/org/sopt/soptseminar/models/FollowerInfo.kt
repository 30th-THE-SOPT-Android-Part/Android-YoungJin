package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FollowerInfo(
    val name: String,
    val profile: String,
    val url: String,
    val description: String? = null,
    val id: String = UUID.randomUUID().toString()
) : Parcelable