package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseActivity
import org.sopt.soptseminar.databinding.ActivityFollowerDetailBinding
import org.sopt.soptseminar.domain.models.github.FollowerInfo
import org.sopt.soptseminar.presentation.github.viewmodels.FollowerDetailViewModel

@AndroidEntryPoint
class FollowerDetailActivity :
    BaseActivity<ActivityFollowerDetailBinding>(R.layout.activity_follower_detail) {
    private val viewModel: FollowerDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        intent.getParcelableExtra<FollowerInfo>(ARG_FOLLOWER_INFO)?.let { follower ->
            viewModel.setFollowerInfo(follower)
        }

        initLayout()
    }

    private fun initLayout() {
        binding.back.setOnClickListener {
            onBackPressed()
        }

        Glide.with(binding.image).load(viewModel.getFollowerInfo()?.profile).into(binding.image)
        binding.image.clipToOutline = true
    }

    fun moveToGithub(view: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.getFollowerInfo()?.url)))
    }

    companion object {
        private const val TAG = "FollowerDetailActivity"
        private const val ARG_FOLLOWER_INFO = "followerInfo"
    }
}