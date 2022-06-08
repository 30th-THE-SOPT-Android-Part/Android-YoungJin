package org.sopt.soptseminar.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.soptseminar.databinding.FragmentOnboardingThirdBinding
import org.sopt.soptseminar.presentation.sign.screens.SignInActivity

class OnboardingThirdFragment : Fragment() {
    private var _binding: FragmentOnboardingThirdBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardingThirdBinding.inflate(inflater, container, false)

        addListeners()

        return binding.root
    }

    private fun addListeners() {
        binding.start.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
            requireActivity().finish()
        }
    }
}