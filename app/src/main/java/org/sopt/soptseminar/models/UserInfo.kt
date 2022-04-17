package org.sopt.soptseminar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    var name: String,
    val age: Int,
    val mbti: String,
    val profile: String? = null,
    val part: SoptPart = SoptPart.AOS,
    val university: String,
    val major: String,
    val email: String,
): Parcelable