package org.sopt.soptseminar.modules.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.sopt.soptseminar.models.UserInfo
import org.sopt.soptseminar.models.types.SoptPartType
import org.sopt.soptseminar.util.safeValueOf
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferenceManager @Inject constructor(@ApplicationContext context: Context) :
    UserPreferenceRepository {
    private val dataStore = context.dataStore

    override suspend fun getUsersPreference(): Flow<UserInfo?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // 임시로 유저 이름이 존재하지 않는 경우, 미가입자로 판단
            if (preferences[KEY_NAME] == null) null
            else UserInfo(
                preferences[KEY_NAME] ?: "",
                preferences[KEY_AGE] ?: 0,
                preferences[KEY_MBTI] ?: "",
                preferences[KEY_PROFILE],
                safeValueOf<SoptPartType>(preferences[KEY_SOPT_PART]) ?: SoptPartType.AOS,
                preferences[KEY_UNIVERSITY] ?: "",
                preferences[KEY_MAJOR] ?: "",
                preferences[KEY_EMAIL] ?: ""
            )
        }

    override suspend fun setUserPreference(userInfo: UserInfo) {
        dataStore.edit { pref ->
            pref[KEY_NAME] = userInfo.name
            pref[KEY_AGE] = userInfo.age
            pref[KEY_MBTI] = userInfo.mbti
            userInfo.profile?.let { pref[KEY_PROFILE] = it }
            pref[KEY_SOPT_PART] = userInfo.part.name
            pref[KEY_UNIVERSITY] = userInfo.university
            pref[KEY_MAJOR] = userInfo.major
            pref[KEY_EMAIL] = userInfo.email
        }
    }

    companion object {
        private val KEY_NAME = stringPreferencesKey("name")
        private val KEY_AGE = intPreferencesKey("age")
        private val KEY_MBTI = stringPreferencesKey("mbti")
        private val KEY_PROFILE = stringPreferencesKey("profile")
        private val KEY_SOPT_PART = stringPreferencesKey("sopt_part")
        private val KEY_UNIVERSITY = stringPreferencesKey("university")
        private val KEY_MAJOR = stringPreferencesKey("major")
        private val KEY_EMAIL = stringPreferencesKey("email")
    }
}