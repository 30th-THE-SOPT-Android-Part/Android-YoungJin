package org.sopt.soptseminar.modules.datastore

import kotlinx.coroutines.flow.Flow
import org.sopt.soptseminar.models.UserInfo

interface UserPreferenceRepository {
    suspend fun getUsersPreference(): Flow<UserInfo>
    suspend fun setUserPreference(userInfo: UserInfo)
}