package org.sopt.soptseminar.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivityHomeBinding
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.UserInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.screens.GithubProfileActivity
import org.sopt.soptseminar.presentation.profile.ProfileViewModel

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@HomeActivity

        viewModel.fetchGithubList()

        intent.getParcelableExtra<UserInfo>(ARG_USER_INFO)?.let { user ->
            viewModel.setUserInfo(user)
        }

        setGithubProfileInfoResult()
        initLayout()
    }

    private fun initLayout() {
        binding.profileImg.clipToOutline = true
        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.blog.movementMethod = LinkMovementMethod.getInstance()
    }

    fun moveToGithubProfile(view: View) {
        val position = when (view) {
            binding.followingContainer -> GithubDetailViewType.FOLLOWING.ordinal
            binding.repositoryContainer -> GithubDetailViewType.REPOSITORIES.ordinal
            else -> GithubDetailViewType.FOLLOWER.ordinal
        }

        Intent(this, GithubProfileActivity::class.java).apply {
            putExtra(
                ARG_GITHUB_INFO, bundleOf(
                    ARG_GITHUB_DETAIL_POSITION to position,
                    ARG_FOLLOWER_LIST to viewModel.getFollower(),
                    ARG_FOLLOWING_LIST to viewModel.getFollowing(),
                    ARG_REPOSITORY_LIST to viewModel.getRepositories().value
                )
            )
            resultLauncher.launch(this)
        }
    }

    private fun setGithubProfileInfoResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
                val data = result.data ?: return@registerForActivityResult
                data.getBundleExtra(ARG_GITHUB_PROFILE_INFO)?.let { bundle ->
                    viewModel.setRepositories(bundle[ARG_REPOSITORY_LIST_RESULT] as? List<RepositoryInfo>)
                }
                // TODO 팔로워, 팔로잉 목록 추가
            }
    }

    companion object {
        private const val TAG = "HomeActivity"
        private const val ARG_USER_INFO = "userInfo"
        private const val ARG_GITHUB_INFO = "userInfo"
        private const val ARG_GITHUB_DETAIL_POSITION = "githubDetailPosition"
        private const val ARG_FOLLOWER_LIST = "followerList"
        private const val ARG_FOLLOWING_LIST = "followingList"
        private const val ARG_REPOSITORY_LIST = "repositoryList"
        private const val ARG_GITHUB_PROFILE_INFO = "githubProfileInfo"
        private const val ARG_REPOSITORY_LIST_RESULT = "repositoryListResult"
    }
}