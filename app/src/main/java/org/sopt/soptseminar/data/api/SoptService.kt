package org.sopt.soptseminar.data.api

import org.sopt.soptseminar.data.models.RequestSignIn
import org.sopt.soptseminar.data.models.RequestSignUp
import org.sopt.soptseminar.data.models.ResponseSignIn
import org.sopt.soptseminar.data.models.ResponseSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    suspend fun postSignIn(@Body body: RequestSignIn): Response<ResponseSignIn>

    @POST("auth/signup")
    suspend fun postSignUp(@Body body: RequestSignUp): Response<ResponseSignUp>
}