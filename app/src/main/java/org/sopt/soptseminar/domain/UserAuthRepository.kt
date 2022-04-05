package org.sopt.soptseminar.domain

interface UserAuthRepository {
    fun signIn()
    fun signUp()
}