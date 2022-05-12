package org.sopt.soptseminar.data.datasources

import org.sopt.soptseminar.data.service.GithubService
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import javax.inject.Inject

class GithubProfileRemoteDataSource @Inject constructor(private val githubService: GithubService) {
    suspend fun fetchFollowers(userName: String): List<FollowerInfo>? {
        runCatching {
            githubService.getFollowerList(userName)
        }.fold({
            return it.body()?.map { follower ->
                follower.toFollowerInfo(follower)
            }
        }, {
            it.printStackTrace()
            return null
        })
    }

    suspend fun fetchFollowing(userName: String): List<FollowerInfo>? {
        runCatching {
            githubService.getFollowingList(userName)
        }.fold({
            return it.body()?.map { following ->
                following.toFollowerInfo(following)
            }
        }, {
            it.printStackTrace()
            return null
        })
    }

    suspend fun fetchRepositories(userName: String): List<RepositoryInfo>? {
        runCatching {
            githubService.getRepositoryList(userName)
        }.fold({
            return it.body()?.map { repository ->
                repository.toRepositoryInfo(repository)
            }
        }, {
            it.printStackTrace()
            return null
        })
    }

    companion object {
        private const val TAG = "GithubProfileRemoteDataSource"
    }
}