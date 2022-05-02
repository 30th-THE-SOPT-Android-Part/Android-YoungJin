package org.sopt.soptseminar.presentation.profile

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseFragment
import org.sopt.soptseminar.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ProfileFragment

        initLayout()
    }

    private fun initLayout() {
        binding.profileImg.clipToOutline = true
        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.blog.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }
}