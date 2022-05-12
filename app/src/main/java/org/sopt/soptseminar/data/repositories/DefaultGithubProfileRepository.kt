package org.sopt.soptseminar.data.repositories

import org.sopt.soptseminar.data.datasources.GithubProfileRemoteDataSource
import org.sopt.soptseminar.domain.repositories.GithubProfileRepository
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.domain.models.github.RepositoryInfo
import javax.inject.Inject

class DefaultGithubProfileRepository @Inject constructor(
    private val remoteDataSource: GithubProfileRemoteDataSource
) : GithubProfileRepository {
    override suspend fun fetchGithubFollowers(userName: String): List<FollowerInfo>? {
        return remoteDataSource.fetchFollowers(userName)
    }

    override suspend fun fetchGithubFollowing(userName: String): List<FollowerInfo>? {
        return remoteDataSource.fetchFollowing(userName)
    }

    override suspend fun fetchGithubRepositories(userName: String): List<RepositoryInfo>? {
        return remoteDataSource.fetchRepositories(userName)
    }
}