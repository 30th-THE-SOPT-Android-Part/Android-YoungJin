package org.sopt.soptseminar.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.models.UserInfo
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private var userInfo = MutableLiveData<UserInfo>()

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo.value = userInfo
    }

    fun getUserInfo(): LiveData<UserInfo> = userInfo
}