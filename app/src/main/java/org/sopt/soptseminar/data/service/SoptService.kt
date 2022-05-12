package org.sopt.soptseminar.data.service

import org.sopt.soptseminar.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    suspend fun postSignIn(@Body body: RequestSignIn): Response<BaseResponse<ResponseSignIn>>

    @POST("auth/signup")
    suspend fun postSignUp(@Body body: RequestSignUp): Response<BaseResponse<ResponseSignUp>>
}