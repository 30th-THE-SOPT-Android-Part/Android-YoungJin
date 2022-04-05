package org.sopt.soptseminar.presentation.sign.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(

) : ViewModel() {
    private val userName = MutableLiveData<String>()
    private val userId = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()

    fun signIn() {
        TODO("Not yet implemented")
    }

    fun signUp() {
        TODO("Not yet implemented")
    }

    fun onUserNameTextChanged(s: CharSequence, start: Int, before: Int, count:Int) {
        userName.value = s.toString().trim()
    }

    fun onUserIdTextChanged(s: CharSequence, start: Int, before: Int, count:Int) {
        userId.value = s.toString().trim()
    }

    fun onUserPasswordTextChanged(s: CharSequence, start: Int, before: Int, count:Int) {
        userPassword.value = s.toString().trim()
    }

    fun getUserName(): LiveData<String> = userName
    fun getUserId(): LiveData<String> = userId
    fun getUserPassword(): LiveData<String> = userPassword
}