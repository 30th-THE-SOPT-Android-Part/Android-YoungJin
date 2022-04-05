package org.sopt.soptseminar.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.databinding.DataBindingUtil
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initLayout()
    }

    private fun initLayout() {
        binding.profileImg.clipToOutline = true

        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.blog.movementMethod = LinkMovementMethod.getInstance()
    }
}