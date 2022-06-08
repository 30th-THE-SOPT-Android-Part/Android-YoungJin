package org.sopt.soptseminar.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.soptseminar.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : Fragment() {
    private var _binding: FragmentOnboardingFirstBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardingFirstBinding.inflate(inflater, container, false)

        return binding.root
    }
}