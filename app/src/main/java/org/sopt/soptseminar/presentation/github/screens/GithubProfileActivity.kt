package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivityGithubProfileBinding
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.adapters.GithubAdapter
import org.sopt.soptseminar.presentation.profile.ProfileViewModel
import org.sopt.soptseminar.presentation.sign.screens.SignInActivity

@AndroidEntryPoint
class GithubProfileActivity :
    BaseActivity<ActivityGithubProfileBinding>(R.layout.activity_github_profile) {
    private val viewModel: ProfileViewModel by viewModels()

    private var position: Int = 0
    private val tabTitles = arrayOf(
        GithubDetailViewType.FOLLOWER.strRes,
        GithubDetailViewType.REPOSITORIES.strRes,
        GithubDetailViewType.FOLLOWING.strRes,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        intent.getBundleExtra(ARG_GITHUB_INFO)?.let {
            handleArguments(it)
        }

        initLayout()
        addListeners()
    }

    private fun handleArguments(bundle: Bundle) {
        (bundle[ARG_GITHUB_DETAIL_POSITION] as? Int)?.let { position ->
            this.position = position
        }

        viewModel.setFollowers(bundle[ARG_FOLLOWER_LIST] as? List<FollowerInfo>)
        viewModel.setFollowing(bundle[ARG_FOLLOWING_LIST] as? List<FollowerInfo>)
        viewModel.setRepositories(bundle[ARG_REPOSITORY_LIST] as? List<RepositoryInfo>)
    }

    private fun initLayout() {
        binding.githubDetail.run {
            adapter = GithubAdapter(this@GithubProfileActivity)
            setCurrentItem(position, false)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

    private fun addListeners() {
        binding.back.setOnClickListener {
            backToPrevious()
        }
    }

    private fun backToPrevious() {
        viewModel.getRepositories().observe(this) { repositories ->
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra(
                ARG_GITHUB_PROFILE_INFO, bundleOf(
                    ARG_REPOSITORY_LIST_RESULT to repositories
                )
            )
            setResult(RESULT_OK, intent)
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "GithubProfileActivity"
        private const val ARG_GITHUB_INFO = "userInfo"
        const val ARG_GITHUB_DETAIL_POSITION = "githubDetailPosition"
        const val ARG_FOLLOWER_LIST = "followerList"
        const val ARG_FOLLOWING_LIST = "followingList"
        const val ARG_REPOSITORY_LIST = "repositoryList"
        private const val ARG_GITHUB_PROFILE_INFO = "githubProfileInfo"
        private const val ARG_REPOSITORY_LIST_RESULT = "repositoryListResult"
    }
}