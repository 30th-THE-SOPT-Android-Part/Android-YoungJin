package org.sopt.soptseminar.presentation.github.screens

import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseFragment
import org.sopt.soptseminar.databinding.FragmentGithubBinding
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.adapters.GithubAdapter
import org.sopt.soptseminar.presentation.github.viewmodels.GithubViewModel

@AndroidEntryPoint
class GithubFragment : BaseFragment<FragmentGithubBinding>(R.layout.fragment_github) {
    private val viewModel: GithubViewModel by hiltNavGraphViewModels(R.id.github_nav_graph)
    private val tabTitles = arrayOf(
        GithubDetailViewType.FOLLOWER.strRes,
        GithubDetailViewType.REPOSITORIES.strRes,
        GithubDetailViewType.FOLLOWING.strRes,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@GithubFragment

        initLayout()
    }

    private fun initLayout() {
        binding.githubDetail.run {
            adapter = GithubAdapter(requireActivity())
            setCurrentItem(GithubDetailViewType.FOLLOWER.ordinal, false)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

    companion object {
        private const val TAG = "GithubFragment"
    }
}