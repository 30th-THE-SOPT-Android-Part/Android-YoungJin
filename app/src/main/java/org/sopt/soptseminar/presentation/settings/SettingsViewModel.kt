package org.sopt.soptseminar.presentation.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.data.models.db.UserDao
import org.sopt.soptseminar.util.UserSharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userSharedPreferencesManager: UserSharedPreferencesManager,
    private val userDao: UserDao,
) : ViewModel() {

    fun logout() {
        // 1. EncryptedSharedPreferences 사용
        userSharedPreferencesManager.clearUserInfo()

        // 2. Room 사용
//        viewModelScope.launch(Dispatchers.IO) {
//            userDao.deleteUserInfo()
//        }

        // TODO DataStore 버전 추가
    }
}