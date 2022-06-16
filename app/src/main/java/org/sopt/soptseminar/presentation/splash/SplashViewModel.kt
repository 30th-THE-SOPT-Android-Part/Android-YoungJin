package org.sopt.soptseminar.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptseminar.data.models.db.UserDao
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import org.sopt.soptseminar.util.UserSharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferenceRepo: UserPreferenceRepository,
    private val userSharedPreferencesManager: UserSharedPreferencesManager,
    private val userDao: UserDao,
) : ViewModel() {
    private val isSignedUser = MutableLiveData<Boolean>()

    init {
        checkSignedUser()
    }

    private fun checkSignedUser() {
        viewModelScope.launch {
            // 1. EncryptedSharedPreferences 사용
            isSignedUser.value = userSharedPreferencesManager.getUserInfo() != null

            // 2. DataStroe 사용
            // TODO 7주차 과제 제출 후 주석 제거
//            isSignedUser.value = userPreferenceRepo.getUsersPreference().first() != null

            // 3. Room 사용
//            withContext(Dispatchers.IO) {
//                isSignedUser.postValue(userDao.getUserInfo()?.also {
//                    it.toUserInfo(it)
//                } != null)
//            }
        }
    }

    fun getSignedUser(): LiveData<Boolean> = isSignedUser

    companion object {
        private const val TAG = "SplashViewModel"
    }
}