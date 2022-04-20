package org.sopt.soptseminar.presentation.github.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import javax.inject.Inject

@HiltViewModel
class GithubProfileViewModel @Inject constructor() : ViewModel() {
    private val userName = "최영진" // TODO delete 로컬에 유저 정보 저장 시 삭제 예정
    private var followers: List<FollowerInfo>? = listOf()
    private var following: List<FollowerInfo>? = listOf()
    private var repositories: List<RepositoryInfo>? = listOf()

    fun setGithubInfoList(
        followers: List<FollowerInfo>?,
        following: List<FollowerInfo>?,
        repositories: List<RepositoryInfo>?
    ) {
        this.followers = followers
        this.following = following
        this.repositories = repositories
    }

    fun getUserName() = userName
    fun getFollower(): List<FollowerInfo>? = followers
    fun getFollowing(): List<FollowerInfo>? = following
    fun getRepositories(): List<RepositoryInfo>? = repositories

    companion object {
        private const val TAG = "GithubProfileViewModel"
    }
}