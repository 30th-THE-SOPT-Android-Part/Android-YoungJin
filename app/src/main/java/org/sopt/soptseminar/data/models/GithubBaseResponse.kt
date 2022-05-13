package org.sopt.soptseminar.data.models

data class GithubBaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)
