package org.sopt.soptseminar.data.models

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)
