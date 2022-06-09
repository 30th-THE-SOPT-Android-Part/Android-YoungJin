package org.sopt.soptseminar.presentation.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.util.UserSharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userSharedPreferencesManager: UserSharedPreferencesManager,
) : ViewModel() {

    fun logout() {
        userSharedPreferencesManager.clearUserInfo()
    }
}