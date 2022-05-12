package org.sopt.soptseminar.presentation.github.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.soptseminar.domain.repositories.GithubProfileRepository
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.domain.models.github.RepositoryInfo
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubProfileRepo: GithubProfileRepository,
    private val userPreferenceRepo: UserPreferenceRepository,
) : ViewModel() {
    private val userInfo = MutableLiveData<UserInfo>()
    private var followers = MutableLiveData<List<FollowerInfo>?>(mutableListOf())
    private var following = MutableLiveData<List<FollowerInfo>?>(mutableListOf())
    private var repositories = MutableLiveData<MutableList<RepositoryInfo>>(mutableListOf())

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
        fetchGithubList()
    }

    private suspend fun loadUserInfo() {
        userInfo.value = userPreferenceRepo.getUsersPreference().first()
    }

    private fun fetchGithubList() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO UserInfo에 github 전용 username 추가 후, userInfo.githubUserName 으로 접근
            followers.postValue(githubProfileRepo.fetchGithubFollowers("youngjinc"))
            following.postValue(githubProfileRepo.fetchGithubFollowing("youngjinc"))
            repositories.postValue(githubProfileRepo.fetchGithubRepositories("youngjinc")
                ?.toMutableList())
        }
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
    fun getFollower(): LiveData<List<FollowerInfo>?> = followers
    fun getFollowing(): LiveData<List<FollowerInfo>?> = following
    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> = repositories
}