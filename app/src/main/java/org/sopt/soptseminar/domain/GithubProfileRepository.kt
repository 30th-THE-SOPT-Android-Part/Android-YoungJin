package org.sopt.soptseminar.domain

import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo

interface GithubProfileRepository {
    suspend fun fetchGithubFollowers(userName: String): List<FollowerInfo>?
    suspend fun fetchGithubFollowing(userName: String): List<FollowerInfo>?
    suspend fun fetchGithubRepositories(userName: String): List<RepositoryInfo>?
}