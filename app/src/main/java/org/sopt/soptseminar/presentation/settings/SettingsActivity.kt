package org.sopt.soptseminar.presentation.settings

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivitySettingsBinding
import org.sopt.soptseminar.presentation.onboarding.OnboardingActivity

@AndroidEntryPoint
class SettingsActivity :
    BaseActivity<ActivitySettingsBinding>(R.layout.activity_settings) {
    private val viewModel: SettingsViewModel by viewModels()

    fun moveToPrevious(view: View) {
        finish()
    }

    fun moveToOnboarding(view: View) {
        viewModel.logout()
        startActivity(Intent(this, OnboardingActivity::class.java))
        ActivityCompat.finishAffinity(this)
    }
}