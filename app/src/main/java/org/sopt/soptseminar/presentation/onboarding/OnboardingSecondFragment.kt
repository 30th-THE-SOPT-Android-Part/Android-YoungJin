package org.sopt.soptseminar.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.FragmentOnboardingSecondBinding

class OnboardingSecondFragment : Fragment() {
    private var _binding: FragmentOnboardingSecondBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardingSecondBinding.inflate(inflater, container, false)

        addListeners()

        return binding.root
    }

    private fun addListeners() {
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_second_fragment_to_third_fragment)
        }
    }
}