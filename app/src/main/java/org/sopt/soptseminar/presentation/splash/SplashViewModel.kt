package org.sopt.soptseminar.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferenceRepo: UserPreferenceRepository
) : ViewModel() {
    private val isSignedUser = MutableLiveData<Boolean>()

    init {
        checkSignedUser()
    }

    private fun checkSignedUser() {
        viewModelScope.launch {
            isSignedUser.value = userPreferenceRepo.getUsersPreference().first() != null
        }
    }

    fun getSignedUser(): LiveData<Boolean> = isSignedUser

    companion object {
        private const val TAG = "SplashViewModel"
    }
}