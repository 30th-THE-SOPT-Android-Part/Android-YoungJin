package org.sopt.soptseminar.presentation.home

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.ActivityHomeBinding
import org.sopt.soptseminar.models.UserInfo

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@HomeActivity

        intent.getParcelableExtra<UserInfo>(ARG_USER_INFO)?.let { user ->
            viewModel.setUserInfo(user)
        }

        initLayout()
    }

    private fun initLayout() {
        binding.profileImg.clipToOutline = true

        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.blog.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        const val ARG_USER_INFO = "userInfo"
    }
}