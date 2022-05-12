package org.sopt.soptseminar.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
) : ViewModel() {
    private val userInfo = MutableLiveData<UserInfo>()
    private var following: MutableList<FollowerInfo>? = mutableListOf()

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
    }

    private suspend fun loadUserInfo() {
        userInfo.value = userPreferenceRepository.getUsersPreference().first()
    }

    fun getUserInfo(): LiveData<UserInfo> = userInfo
}