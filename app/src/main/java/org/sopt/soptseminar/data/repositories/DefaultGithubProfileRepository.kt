package org.sopt.soptseminar.data.repositories

import org.sopt.soptseminar.data.datasources.GithubProfileRemoteDataSource
import org.sopt.soptseminar.domain.GithubProfileRepository
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import javax.inject.Inject

class DefaultGithubProfileRepository @Inject constructor(
    private val remoteDataSource: GithubProfileRemoteDataSource
) : GithubProfileRepository {
    override fun fetchGithubFollowers(): List<FollowerInfo> {
        return remoteDataSource.fetchFollowers()
    }

    override fun fetchGithubFollowing(): List<FollowerInfo> {
        return remoteDataSource.fetchFollowing()
    }

    override fun fetchGithubRepositories(): List<RepositoryInfo> {
        return remoteDataSource.fetchRepositories()
    }
}