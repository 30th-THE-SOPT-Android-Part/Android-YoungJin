package org.sopt.soptseminar.presentation.github.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.domain.GithubProfileRepository
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.UserInfo
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val profileRepo: GithubProfileRepository,
) : ViewModel() {
    val userInfo = UserInfo( // TODO local에 저장
            name = "최영진",
            age = 24,
            mbti = "ISFP",
            university = "성신여대",
            major = "컴퓨터공학과",
            email = "cyjin6789@gmail.com"
        )

    private var followers: MutableList<FollowerInfo>? = mutableListOf()
    private var following: MutableList<FollowerInfo>? = mutableListOf()
    private var repositories = MutableLiveData<MutableList<RepositoryInfo>>(mutableListOf())


    init {
        fetchGithubList()
    }

    private fun fetchGithubList() {
        followers = profileRepo.fetchGithubFollowers().toMutableList()
        following = profileRepo.fetchGithubFollowing().toMutableList()
        repositories.value = profileRepo.fetchGithubRepositories().toMutableList()
    }

    fun moveRepository(fromPosition: Int, toPosition: Int) {
        repositories.value = repositories.value?.apply {
            val origin = this[fromPosition]
            removeAt(fromPosition)
            add(toPosition, origin)
        }
    }

    fun removeRepository(position: Int) {
        repositories.value = repositories.value?.apply {
            removeAt(position)
        }
    }

    fun getFollower(): List<FollowerInfo>? = followers
    fun getFollowing(): List<FollowerInfo>? = following
    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> = repositories
}