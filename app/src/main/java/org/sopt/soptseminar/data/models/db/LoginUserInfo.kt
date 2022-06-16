package org.sopt.soptseminar.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.models.types.SoptPartType

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
) {
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