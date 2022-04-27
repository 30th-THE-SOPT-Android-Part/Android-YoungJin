package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class RepositoryInfo(
    val name: String,
    val description: String? = null,
    val url: String,
    val id: String = UUID.randomUUID().toString()
) : Parcelable