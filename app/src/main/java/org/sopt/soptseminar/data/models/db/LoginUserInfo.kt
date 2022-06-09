package org.sopt.soptseminar.data.models.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.models.types.SoptPartType

@Parcelize
@Entity(tableName = "user_table")
data class LoginUserInfo(
    var name: String,
    val age: Int,
    val mbti: String,
    val profile: String? = null,
    val part: SoptPartType = SoptPartType.AOS,
    val university: String,
    val major: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
): Parcelable {
    fun toUserInfo(user: LoginUserInfo): UserInfo {
        return UserInfo(
            user.name,
            user.age,
            user.mbti,
            user.profile,
            user.part,
            user.university,
            user.major,
            user.email
        )
    }
}