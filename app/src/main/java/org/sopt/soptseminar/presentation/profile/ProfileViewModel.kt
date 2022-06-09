package org.sopt.soptseminar.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptseminar.data.models.db.UserDao
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import org.sopt.soptseminar.util.UserSharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferenceRepo: UserPreferenceRepository,
    private val userSharedPreferencesManager: UserSharedPreferencesManager,
    private val userDao: UserDao,
) : ViewModel() {
    private val userInfo = MutableLiveData<UserInfo>()
    private var following: MutableList<FollowerInfo>? = mutableListOf()

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
    }

    private fun loadUserInfo() {
        // 1. EncryptedSharedPreferences 사용
        userInfo.value = userSharedPreferencesManager.getUserInfo()

        // 2. DataStore 사용
//        userInfo.value = userPreferenceRepo.getUsersPreference().first()

        // 3. Room 사용
//        withContext(Dispatchers.IO) {
//            userInfo.postValue(userDao.getUserInfo()?.run {
//                toUserInfo(this)
//            })
//        }
    }

    fun getUserInfo(): LiveData<UserInfo> = userInfo
}