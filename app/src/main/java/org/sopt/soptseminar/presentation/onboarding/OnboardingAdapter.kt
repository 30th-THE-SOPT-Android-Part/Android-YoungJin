package org.sopt.soptseminar.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter (fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFirstFragment()
            1 -> OnboardingSecondFragment()
            else -> OnboardingThirdFragment()
        }
    }

    companion object {
        private const val TAG = "OnboardingAdapter"
    }
}
