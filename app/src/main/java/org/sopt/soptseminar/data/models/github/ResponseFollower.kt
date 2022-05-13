package org.sopt.soptseminar.data.models.github

import com.google.gson.annotations.SerializedName
import org.sopt.soptseminar.domain.models.github.FollowerInfo

data class ResponseFollower(
    val id: Int,
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profile: String,
    @SerializedName("html_url")
    val url: String,
) {
    fun toFollowerInfo(follower: ResponseFollower): FollowerInfo =
        FollowerInfo(follower.name, follower.profile, follower.url, id = follower.id)
}