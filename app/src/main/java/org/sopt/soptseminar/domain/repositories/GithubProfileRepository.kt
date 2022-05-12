package org.sopt.soptseminar.domain.repositories

import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.domain.models.github.RepositoryInfo

interface GithubProfileRepository {
    suspend fun fetchGithubFollowers(userName: String): List<FollowerInfo>?
    suspend fun fetchGithubFollowing(userName: String): List<FollowerInfo>?
    suspend fun fetchGithubRepositories(userName: String): List<RepositoryInfo>?
}