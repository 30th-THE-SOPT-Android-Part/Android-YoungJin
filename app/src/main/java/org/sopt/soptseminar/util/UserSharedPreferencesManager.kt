package org.sopt.soptseminar.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.models.types.SoptPartType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSharedPreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val prefs = EncryptedSharedPreferences.create(
        "org.sopt.soptseminar.USER_PREFERENCES",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setUserInfo(user: UserInfo) {
        prefs.edit().run {
            putString(PREF_USER_EMAIL, user.email)
            putString(PREF_USER_NAME, user.name)
            putInt(PREF_USER_AGE, user.age)
            putString(PREF_USER_MBTI, user.mbti)
            putString(PREF_USER_PROFILE, user.profile)
            putString(PREF_USER_PART, user.part.name)
            putString(PREF_USER_UNIV, user.university)
            putString(PREF_USER_MAJOR, user.major)
        }.apply()
    }

    fun getUserInfo(): UserInfo? {
        val name = prefs.getString(PREF_USER_NAME, null)
        val email = prefs.getString(PREF_USER_EMAIL, null)

        // 유저 이름이 존재하지 않는 경우, 미가입자로 판단
        if (name == null || email == null) return null
        return UserInfo(
            name,
            prefs.getInt(PREF_USER_AGE, 0),
            prefs.getString(PREF_USER_MBTI, null) ?: "",
            prefs.getString(PREF_USER_PROFILE, null),
            safeValueOf<SoptPartType>(prefs.getString(PREF_USER_PART, null)) ?: SoptPartType.AOS,
            prefs.getString(PREF_USER_UNIV, null) ?: "",
            prefs.getString(PREF_USER_MAJOR, null) ?: "",
            email
        )
    }

    fun clearUserInfo() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_USER_EMAIL = "userEmail"
        private const val PREF_USER_NAME = "userName"
        private const val PREF_USER_AGE = "userAge"
        private const val PREF_USER_MBTI = "userMbti"
        private const val PREF_USER_PROFILE = "userProfile"
        private const val PREF_USER_PART = "userPart"
        private const val PREF_USER_UNIV = "userUniv"
        private const val PREF_USER_MAJOR = "userMajor"
    }
}