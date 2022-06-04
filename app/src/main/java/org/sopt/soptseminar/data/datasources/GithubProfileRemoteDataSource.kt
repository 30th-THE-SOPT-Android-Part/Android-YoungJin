package org.sopt.soptseminar.data.datasources

import org.sopt.soptseminar.data.services.GithubService
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.domain.models.github.RepositoryInfo
import javax.inject.Inject

class GithubProfileRemoteDataSource @Inject constructor(private val githubService: GithubService) {
    suspend fun fetchFollowers(userName: String): List<FollowerInfo>? {
        return runCatching {
            githubService.getFollowerList(userName)
        }.fold({
            it.body()?.map { follower ->
                follower.toFollowerInfo(follower)
            }
        }, {
            it.printStackTrace()
            null
        })
    }

    suspend fun fetchFollowing(userName: String): List<FollowerInfo>? {
        return runCatching {
            githubService.getFollowingList(userName)
        }.fold({
            it.body()?.map { following ->
                following.toFollowerInfo(following)
            }
        }, {
            it.printStackTrace()
            null
        })
    }

    suspend fun fetchRepositories(userName: String): List<RepositoryInfo>? {
        return runCatching {
            githubService.getRepositoryList(userName)
        }.fold({
            it.body()?.map { repository ->
                repository.toRepositoryInfo(repository)
            }
        }, {
            it.printStackTrace()
            null
        })
    }

    companion object {
        private const val TAG = "GithubProfileRemoteDataSource"
    }
}