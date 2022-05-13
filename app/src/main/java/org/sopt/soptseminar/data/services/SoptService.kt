package org.sopt.soptseminar.data.services

import org.sopt.soptseminar.data.models.*
import org.sopt.soptseminar.data.models.sign.RequestSignIn
import org.sopt.soptseminar.data.models.sign.RequestSignUp
import org.sopt.soptseminar.data.models.sign.ResponseSignIn
import org.sopt.soptseminar.data.models.sign.ResponseSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    suspend fun postSignIn(@Body body: RequestSignIn): Response<GithubBaseResponse<ResponseSignIn>>

    @POST("auth/signup")
    suspend fun postSignUp(@Body body: RequestSignUp): Response<GithubBaseResponse<ResponseSignUp>>
}