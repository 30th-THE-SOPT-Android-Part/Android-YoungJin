package org.sopt.soptseminar.domain

interface UserAuthRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(name: String, email: String, password: String): Boolean
}