package org.sopt.soptseminar.data.models

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val data: Data,
) {
    data class Data(
        val name: String,
        val email: String
    )
}
