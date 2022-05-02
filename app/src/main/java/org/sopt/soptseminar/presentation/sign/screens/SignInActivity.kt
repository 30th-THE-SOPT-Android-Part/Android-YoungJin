package org.sopt.soptseminar.presentation.sign.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivitySignInBinding
import org.sopt.soptseminar.models.SignInfo
import org.sopt.soptseminar.models.UserInfo
import org.sopt.soptseminar.presentation.MainActivity
import org.sopt.soptseminar.presentation.sign.viewmodels.SignViewModel
import org.sopt.soptseminar.util.extensions.showToast

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel: SignViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignInActivity

        setSignUpResult()
        addListeners()
        addObservers()
    }

    private fun addListeners() {
        binding.signUp.setOnClickListener {
            resultLauncher.launch(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun addObservers() {
        viewModel.getValidSignInput().observe(this) { isValid ->
            if (isValid) {
                val name = viewModel.getUserInfo()?.name
                showToast(String.format(getString(R.string.sign_in_success_toast_text), name))
                moveToHome()
            } else {
                showToast(getString(R.string.check_sign_in_input_toast_text))
            }
        }
    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
                val data = result.data ?: return@registerForActivityResult
                data.getParcelableExtra<UserInfo>(ARG_USER_INFO)?.let { user ->
                    viewModel.setUserInfo(user)
                }
                data.getParcelableExtra<SignInfo>(ARG_SIGN_INFO)?.let { sign ->
                    viewModel.setSignInfo(sign)
                }
            }
    }

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        const val ARG_USER_INFO = "userInfo"
        const val ARG_SIGN_INFO = "signInfo"
    }
}