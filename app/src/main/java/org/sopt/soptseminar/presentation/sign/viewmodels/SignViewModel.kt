package org.sopt.soptseminar.presentation.sign.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.soptseminar.domain.repositories.UserAuthRepository
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val userAuthRepo: UserAuthRepository,
) : ViewModel() {
    private val userName = MutableLiveData<String?>()
    private val userId = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()
    private val isSuccessSign = MutableLiveData<Boolean?>()

    fun signIn() {
        val isValid = !(userId.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
        if (!isValid) return

        viewModelScope.launch(Dispatchers.IO) {
            val response = userAuthRepo.signIn(
                userId.value!!,
                userPassword.value!!
            )

            userName.postValue(response.second)
            isSuccessSign.postValue(response.first)
        }
    }

    fun signUp() {
        val isValid =
            !(userId.value.isNullOrEmpty() || userName.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
        if (!isValid) return

        viewModelScope.launch(Dispatchers.IO) {
            isSuccessSign.postValue(
                userAuthRepo.signUp(
                    userName.value!!,
                    userId.value!!,
                    userPassword.value!!
                )
            )
        }
    }

    fun onUserNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userName.value = s.toString().trim()
    }

    fun onUserIdTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userId.value = s.toString().trim()
    }

    fun onUserPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userPassword.value = s.toString().trim()
    }

    fun setSignInfo(email: String?, password: String?) {
        if (email == null || password == null) return
        userId.value = email!!
        userPassword.value = password!!
    }

    fun getUserId(): LiveData<String> = userId
    fun getUserName(): LiveData<String?> = userName
    fun getUserPassword(): LiveData<String> = userPassword
    fun getSuccessSign(): LiveData<Boolean?> = isSuccessSign

    companion object {
        private const val TAG = "SignViewModel"
    }
}