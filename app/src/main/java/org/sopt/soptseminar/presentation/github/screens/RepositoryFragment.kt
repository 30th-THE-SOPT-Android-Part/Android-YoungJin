package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.sopt.soptseminar.databinding.FragmentRepositoryBinding
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.presentation.github.adapters.RepositoryListAdapter
import org.sopt.soptseminar.presentation.github.viewmodels.GithubProfileViewModel

class RepositoryFragment : Fragment(), RepositoryListAdapter.OnItemClickListener {
    lateinit var binding: FragmentRepositoryBinding
    private val viewModel: GithubProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initLayout()

        return binding.root
    }

    private fun initLayout() {
        RepositoryListAdapter().run {
            binding.repositoryList.adapter = this
            setOnItemClickListener(this@RepositoryFragment)
            submitList(viewModel.getRepositories())
        }
    }

    override fun onItemClick(item: RepositoryInfo) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    companion object {
        private const val TAG = "RepositoryFragment"
    }
}