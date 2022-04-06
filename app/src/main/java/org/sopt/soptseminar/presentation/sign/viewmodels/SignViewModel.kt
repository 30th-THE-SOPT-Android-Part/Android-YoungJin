package org.sopt.soptseminar.presentation.sign.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.models.SignInfo
import org.sopt.soptseminar.models.UserInfo
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor() : ViewModel() {
    private val userName = MutableLiveData<String>()
    private val userId = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()
    private val isValidSignInput = MutableLiveData<Boolean>()
    private var userInfo: UserInfo = UserInfo(name = "", age = 24, mbti ="ISFP", university = "성신여대", major = "컴퓨터공학과", email = "cyjin6789@gmail.com")
    private var signInfo: SignInfo? = null

    fun signIn() {
        val isValid = !(userId.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
        if (isValid) {
            signInfo = SignInfo(
                id = userId.value!!,
                password = userPassword.value!!
            )
            userInfo.name =  userName.value ?: "최영진"

            // TODO Implement the signin process
        }

        isValidSignInput.value = isValid
    }

    fun signUp() {
        val isValid = !(userId.value.isNullOrEmpty() || userName.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
        if (isValid) {
            signInfo = SignInfo(
                id = userId.value!!,
                password = userPassword.value!!
            )
            userInfo.name =  userName.value!!

            // TODO Implement the signin process
        }

        isValidSignInput.value = isValid
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

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
        userName.value = userInfo.name
    }

    fun setSignInfo(signInfo: SignInfo) {
        this.signInfo = signInfo
        userId.value = signInfo.id
        userPassword.value = signInfo.password
    }

    fun getUserName(): LiveData<String> = userName
    fun getUserId(): LiveData<String> = userId
    fun getUserPassword(): LiveData<String> = userPassword
    fun getUserInfo(): UserInfo? = userInfo
    fun getSignInfo(): SignInfo? = signInfo
    fun getValidSignInput(): LiveData<Boolean> = isValidSignInput
}