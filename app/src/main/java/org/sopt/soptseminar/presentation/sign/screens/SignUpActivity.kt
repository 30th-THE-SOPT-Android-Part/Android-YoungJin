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
        viewModel.getSuccessSign().observe(this) { isSuccess ->
            if (isSuccess == true) {
                moveToSignIn()
            } else {
                showToast(getString(R.string.sign_up_failure_toast_text))
            }
        }
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra(ARG_USER_EMAIL, binding.idInput.text.toString())
        intent.putExtra(ARG_USER_PASSWORD, binding.passwordInput.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val ARG_USER_EMAIL = "userEmail"
        const val ARG_USER_PASSWORD = "userPassword"
    }
}