package org.sopt.soptseminar.presentation.settings

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivitySettingsBinding
import org.sopt.soptseminar.presentation.onboarding.screens.OnboardingActivity
import org.sopt.soptseminar.util.extensions.showToast

@AndroidEntryPoint
class SettingsActivity :
    BaseActivity<ActivitySettingsBinding>(R.layout.activity_settings) {
    private val viewModel: SettingsViewModel by viewModels()

    fun moveToPrevious(view: View) {
        finish()
    }

    fun moveToOnboarding(view: View) {
        viewModel.logout()
        showToast(getString(R.string.settings_logout_success_toast_text))
        startActivity(Intent(this, OnboardingActivity::class.java))
        ActivityCompat.finishAffinity(this)
    }
}