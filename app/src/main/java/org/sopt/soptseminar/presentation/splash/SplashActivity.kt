package org.sopt.soptseminar.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivitySplashBinding
import org.sopt.soptseminar.presentation.MainActivity
import org.sopt.soptseminar.presentation.sign.screens.SignInActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel: SplashViewModel by viewModels()
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addListeners()
    }

    private fun addListeners() {
        viewModel.getSignedUser().observe(this) { isSigned ->
            lifecycleScope.launch(Dispatchers.Main) {
                job = launch {
                    delay(2000)
                    moveToNext(isSigned)
                    finish()
                }
            }
        }
    }

    private fun moveToNext(isSigned: Boolean) {
        lifecycleScope.launch {
            if (isSigned) startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            else startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
        }
    }

    override fun onPause() {
        job?.cancel()
        super.onPause()
    }

    companion object {
        private const val TAG = "SplashActivity"
    }
}
