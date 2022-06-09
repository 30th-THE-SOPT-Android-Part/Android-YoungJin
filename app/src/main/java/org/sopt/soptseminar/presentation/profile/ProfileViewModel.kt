package org.sopt.soptseminar.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.soptseminar.data.models.db.UserDao
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferenceRepo: UserPreferenceRepository,
    private val userDao: UserDao,
) : ViewModel() {
    private val userInfo = MutableLiveData<UserInfo>()
    private var following: MutableList<FollowerInfo>? = mutableListOf()

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
    }

    private suspend fun loadUserInfo() {
        // TODO EncryptedSharedPreferences 버전 추가

        // 1. DataStore 사용
//        userInfo.value = userPreferenceRepo.getUsersPreference().first()

        // 2. Room 사용
        withContext(Dispatchers.IO) {
            userInfo.postValue(userDao.getUserInfo()?.run {
                toUserInfo(this)
            })
        }
    }

    // TODO EncryptedSharedPreferences 버전 추가

    fun getUserInfo(): LiveData<UserInfo> = userInfo
}