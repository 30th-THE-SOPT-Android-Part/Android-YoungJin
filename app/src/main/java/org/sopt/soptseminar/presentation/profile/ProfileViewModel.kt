package org.sopt.soptseminar.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.soptseminar.domain.GithubProfileRepository
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.UserInfo
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: GithubProfileRepository,
    private val userPreferenceRepository: UserPreferenceRepository,
) : ViewModel() {
    private val userInfo = MutableLiveData<UserInfo>()
    private var followers: MutableList<FollowerInfo>? = mutableListOf()
    private var following: MutableList<FollowerInfo>? = mutableListOf()
    private var repositories = MutableLiveData<MutableList<RepositoryInfo>>(mutableListOf())

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
    }

    private suspend fun loadUserInfo() {
        userInfo.value = userPreferenceRepository.getUsersPreference().first()
    }

    fun fetchGithubList() { // TODO 코드 정리 후 init 블록 안에서 호출..
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

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo.value = userInfo
    }

    fun setFollowers(followers: List<FollowerInfo>?) {
        this.followers = followers?.toMutableList()
    }

    fun setFollowing(following: List<FollowerInfo>?) {
        this.following = following?.toMutableList()
    }

    fun setRepositories(repositories: List<RepositoryInfo>?) {
        this.repositories.value = repositories?.toMutableList()
    }

    fun getUserInfo(): LiveData<UserInfo> = userInfo
    fun getFollower(): List<FollowerInfo>? = followers
    fun getFollowing(): List<FollowerInfo>? = following
    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> = repositories
}