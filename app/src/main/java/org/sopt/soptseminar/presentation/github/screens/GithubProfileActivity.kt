package org.sopt.soptseminar.presentation.github.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.ActivityGithubProfileBinding
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.viewmodels.GithubProfileViewModel

class GithubProfileActivity : AppCompatActivity() {
    private val viewModel: GithubProfileViewModel by viewModels()
    private lateinit var binding: ActivityGithubProfileBinding
    private var position: Int = 0
    private val tabTitles = arrayOf(
        GithubDetailViewType.FOLLOWER.strRes,
        GithubDetailViewType.REPOSITORIES.strRes,
        GithubDetailViewType.FOLLOWING.strRes,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_profile)
        binding.viewModel = viewModel
        intent.getBundleExtra(ARG_GITHUB_INFO)?.let {
            handleArguments(it)
        }

        initializeView()
        addListeners()
    }

    private fun handleArguments(bundle: Bundle) {
        (bundle[ARG_GITHUB_DETAIL_POSITION] as? Int)?.let { position ->
            this.position = position
        }

        viewModel.setGithubInfoList(
            bundle[ARG_FOLLOWER_LIST] as? ArrayList<FollowerInfo>,
            bundle[ARG_FOLLOWING_LIST] as? ArrayList<FollowerInfo>,
            bundle[ARG_REPOSITORY_LIST] as? ArrayList<RepositoryInfo>,
        )
    }

    private fun initializeView() {
        binding.githubDetail.run {
            adapter = GithubDetailAdapter(this@GithubProfileActivity)
            setCurrentItem(position, false)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

    private fun addListeners() {
        binding.back.setOnClickListener {
            super.onBackPressed()
        }
    }

    inner class GithubDetailAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int = GithubDetailViewType.values().size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    FollowerFragment.newInstance(GithubDetailViewType.FOLLOWER)
                }
                1 -> {
                    RepositoryFragment()
                }
                2 -> {
                    FollowerFragment.newInstance(GithubDetailViewType.FOLLOWING)
                }
                else -> FollowerFragment.newInstance(GithubDetailViewType.FOLLOWER)
            }
        }
    }

    companion object {
        private const val TAG = "GithubProfileActivity"
        private const val ARG_GITHUB_INFO = "userInfo"
        const val ARG_GITHUB_DETAIL_POSITION = "githubDetailPosition"
        const val ARG_FOLLOWER_LIST = "followerList"
        const val ARG_FOLLOWING_LIST = "followingList"
        const val ARG_REPOSITORY_LIST = "repositoryList"
    }
}