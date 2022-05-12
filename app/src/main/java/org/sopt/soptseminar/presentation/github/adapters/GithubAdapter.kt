package org.sopt.soptseminar.presentation.github.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.soptseminar.presentation.types.GithubDetailViewType
import org.sopt.soptseminar.presentation.github.screens.FollowerFragment
import org.sopt.soptseminar.presentation.github.screens.RepositoryFragment

class GithubAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = GithubDetailViewType.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment.newInstance(GithubDetailViewType.FOLLOWER)
            1 -> RepositoryFragment()
            else -> FollowerFragment.newInstance(GithubDetailViewType.FOLLOWING)
        }
    }
}