package org.sopt.soptseminar.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.soptseminar.domain.models.types.SoptPartType

@Parcelize
data class UserInfo(
    var name: String,
    val age: Int,
    val mbti: String,
    val profile: String? = null,
    val part: SoptPartType = SoptPartType.AOS,
    val university: String,
    val major: String,
    val email: String,
): Parcelable