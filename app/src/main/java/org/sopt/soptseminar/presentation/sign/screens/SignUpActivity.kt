package org.sopt.soptseminar.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.ActivitySignUpBinding
import org.sopt.soptseminar.presentation.sign.viewmodels.SignViewModel

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel

        addListeners()
        addObservers()
    }

    private fun addListeners() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun addObservers() {
        viewModel.getValidSignInput().observe(this) { isValid ->
            if (isValid) {
                moveToSignIn()
            } else {
                Toast.makeText(
                    this, getString(R.string.sign_up_failure_toast_text), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra(ARG_USER_INFO, viewModel.getUserInfo())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val ARG_USER_INFO = "userInfo"
    }
}