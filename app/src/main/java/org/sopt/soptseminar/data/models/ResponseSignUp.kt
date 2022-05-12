package org.sopt.soptseminar.data.models

data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data,
) {
    data class Data(
        val id: Int
    )
}
