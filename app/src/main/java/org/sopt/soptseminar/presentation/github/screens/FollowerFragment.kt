package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseFragment
import org.sopt.soptseminar.databinding.FragmentFollowerBinding
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.adapters.FollowerListAdapter
import org.sopt.soptseminar.presentation.home.ProfileViewModel

@AndroidEntryPoint
class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower),
    FollowerListAdapter.OnItemClickListener {
    private val viewModel: ProfileViewModel by activityViewModels()
    private var followerViewType: String = GithubDetailViewType.FOLLOWER.name

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
    }

    private fun initLayout() {
        val items = when (followerViewType) {
            GithubDetailViewType.FOLLOWER.name -> viewModel.getFollower()
            GithubDetailViewType.FOLLOWING.name -> viewModel.getFollowing()
            else -> viewModel.getFollower()
        }

        FollowerListAdapter().run {
            binding.followerList.adapter = this
            setOnItemClickListener(this@FollowerFragment)
            submitList(items)
        }
    }

    override fun onItemClick(item: FollowerInfo) {
        val intent = Intent(requireContext(), FollowerDetailActivity::class.java)
        intent.putExtra(ARG_FOLLOWER_INFO, item)
        startActivity(intent)
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