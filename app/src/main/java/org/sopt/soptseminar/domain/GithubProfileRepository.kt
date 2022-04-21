package org.sopt.soptseminar.domain

import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo

interface GithubProfileRepository {
    fun fetchGithubFollowers(): List<FollowerInfo>
    fun fetchGithubFollowing(): List<FollowerInfo>
    fun fetchGithubRepositories(): List<RepositoryInfo>
}