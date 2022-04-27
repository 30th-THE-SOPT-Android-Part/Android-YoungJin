package org.sopt.soptseminar.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivitySignUpBinding
import org.sopt.soptseminar.presentation.sign.viewmodels.SignViewModel
import org.sopt.soptseminar.util.extensions.showToast

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        addListeners()
        addObservers()
    }

    private fun addListeners() {
        binding.back.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun addObservers() {
        viewModel.getValidSignInput().observe(this) { isValid ->
            if (isValid) {
                moveToSignIn()
            } else {
                showToast(getString(R.string.sign_up_failure_toast_text))
            }
        }
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra(ARG_USER_INFO, viewModel.getUserInfo())
        intent.putExtra(ARG_SIGN_INFO, viewModel.getSignInfo())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val ARG_USER_INFO = "userInfo"
        const val ARG_SIGN_INFO = "signInfo"
    }
}