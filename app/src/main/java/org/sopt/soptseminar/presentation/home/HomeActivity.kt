package org.sopt.soptseminar.presentation.home

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.ActivityHomeBinding
import org.sopt.soptseminar.models.UserInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.screens.GithubProfileActivity

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@HomeActivity

        intent.getParcelableExtra<UserInfo>(ARG_USER_INFO).let { user ->
            viewModel.setUserInfo(
                user ?: UserInfo(
                    name = "최영진",
                    age = 24,
                    mbti = "ISFP",
                    university = "성신여대",
                    major = "컴퓨터공학과",
                    email = "cyjin6789@gmail.com"
                )
            )
        }

        initLayout()
    }

    fun moveToGithubDetail(view: View) {
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
                    ARG_REPOSITORY_LIST to viewModel.getRepositories()
                )
            )
            startActivity(this)
        }
    }

    private fun initLayout() {
        binding.profileImg.clipToOutline = true
        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.blog.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        private const val TAG = "HomeActivity"
        private const val ARG_USER_INFO = "userInfo"
        private const val ARG_GITHUB_INFO = "userInfo"
        private const val ARG_GITHUB_DETAIL_POSITION = "githubDetailPosition"
        private const val ARG_FOLLOWER_LIST = "followerList"
        private const val ARG_FOLLOWING_LIST = "followingList"
        private const val ARG_REPOSITORY_LIST = "repositoryList"
    }
}