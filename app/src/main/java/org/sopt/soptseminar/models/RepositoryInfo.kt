package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryInfo(
    val id: Int,
    val name: String,
    val description: String? = null,
    val url: String,
): Parcelable