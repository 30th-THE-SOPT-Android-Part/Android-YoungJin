package org.sopt.soptseminar.presentation.github.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseFragment
import org.sopt.soptseminar.databinding.FragmentFollowerBinding
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.adapters.FollowerListAdapter
import org.sopt.soptseminar.presentation.github.viewmodels.GithubViewModel

@AndroidEntryPoint
class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private val viewModel: GithubViewModel by hiltNavGraphViewModels(R.id.github_nav_graph)
    private var followerViewType: String = GithubDetailViewType.FOLLOWER.name
    private val followerListAdapter = FollowerListAdapter(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString(ARG_FOLLOWER_VIEW_TYPE).let { viewType ->
                if (viewType == null) return
                followerViewType = viewType
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addObservers()
    }

    private fun initLayout() {
        binding.followerList.adapter = followerListAdapter
    }

    private fun addObservers() {
        viewModel.getFollower().observe(viewLifecycleOwner) {
            if (followerViewType == GithubDetailViewType.FOLLOWER.name) {
                followerListAdapter.submitList(it?.toMutableList())
            }
        }

        viewModel.getFollowing().observe(viewLifecycleOwner) {
            if (followerViewType == GithubDetailViewType.FOLLOWING.name) {
                followerListAdapter.submitList(it?.toMutableList())
            }
        }
    }

    private fun onItemClick(item: FollowerInfo) {
        findNavController().navigate(
            R.id.action_follower_to_follower_detail, bundleOf(
                ARG_FOLLOWER_INFO to item
            )
        )
    }

    companion object {
        private const val TAG = "FollowerFragment"
        private const val ARG_FOLLOWER_VIEW_TYPE = "followerViewType"
        private const val ARG_FOLLOWER_INFO = "followerInfo"

        fun newInstance(viewType: GithubDetailViewType) = FollowerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FOLLOWER_VIEW_TYPE, viewType.name)
            }
        }
    }
}