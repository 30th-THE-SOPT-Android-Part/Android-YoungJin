package org.sopt.soptseminar.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.soptseminar.R
import org.sopt.soptseminar.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : Fragment() {
    private var _binding: FragmentOnboardingFirstBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardingFirstBinding.inflate(inflater, container, false)

        addListeners()

        return binding.root
    }

    private fun addListeners() {
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_first_fragment_to_second_fragment)
        }
    }
}