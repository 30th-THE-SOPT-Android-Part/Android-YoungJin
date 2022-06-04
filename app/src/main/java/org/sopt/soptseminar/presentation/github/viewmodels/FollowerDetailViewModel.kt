package org.sopt.soptseminar.presentation.github.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import javax.inject.Inject

@HiltViewModel
class FollowerDetailViewModel @Inject constructor() : ViewModel() {
    private var followerInfo: FollowerInfo? = null

    fun setFollowerInfo(follower: FollowerInfo) {
        this.followerInfo = follower
    }

    fun getFollowerInfo(): FollowerInfo? = followerInfo

    companion object {
        private const val TAG = "DetailViewModel"
    }
}