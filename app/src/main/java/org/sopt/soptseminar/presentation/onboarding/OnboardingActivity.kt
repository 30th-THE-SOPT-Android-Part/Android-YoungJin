package org.sopt.soptseminar.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivityOnboardingBinding
import org.sopt.soptseminar.presentation.sign.screens.SignInActivity

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        binding.onboardingContent.adapter = OnboardingAdapter(this@OnboardingActivity)

        TabLayoutMediator(binding.indicator, binding.onboardingContent){tab, _->
            binding.onboardingContent.setCurrentItem(tab.position, false)
        }.attach()
    }

    private fun addListeners() {
        binding.start.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}
