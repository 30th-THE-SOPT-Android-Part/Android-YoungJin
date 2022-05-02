package org.sopt.soptseminar.presentation.github.viewmodels

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
class GithubViewModel @Inject constructor(
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
        fetchGithubList()
    }

    private suspend fun loadUserInfo() {
        userInfo.value = userPreferenceRepository.getUsersPreference().first()
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

    fun getUserInfo(): LiveData<UserInfo> = userInfo
    fun getFollower(): List<FollowerInfo>? = followers
    fun getFollowing(): List<FollowerInfo>? = following
    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> = repositories
}