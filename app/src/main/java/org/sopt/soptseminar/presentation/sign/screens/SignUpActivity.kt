package org.sopt.soptseminar.presentation.sign.screens

import android.os.Bundle
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

        addListeners()
    }

    private fun addListeners() {
        binding.signUp.setOnClickListener {
            finish()
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
}