package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.R
import org.sopt.soptseminar.base.BaseFragment
import org.sopt.soptseminar.databinding.FragmentRepositoryBinding
import org.sopt.soptseminar.domain.models.github.RepositoryInfo
import org.sopt.soptseminar.presentation.github.adapters.RepositoryListAdapter
import org.sopt.soptseminar.presentation.github.viewmodels.GithubViewModel
import org.sopt.soptseminar.util.ItemTouchHelperCallback

@AndroidEntryPoint
class RepositoryFragment : BaseFragment<FragmentRepositoryBinding>(R.layout.fragment_repository) {
    private val viewModel: GithubViewModel by hiltNavGraphViewModels(R.id.github_nav_graph)
    private lateinit var adapter: RepositoryListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addObservers()
    }

    private fun initLayout() {
        adapter = RepositoryListAdapter(::onItemClick, ::onItemMove, ::onItemSwipe).apply {
            binding.repositoryList.adapter = this
            ItemTouchHelper(ItemTouchHelperCallback(this)).attachToRecyclerView(binding.repositoryList)
        }
    }

    private fun addObservers() {
        viewModel.getRepositories().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            adapter.submitList(it.toMutableList())
        }
    }

    private fun onItemClick(item: RepositoryInfo) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    private fun onItemMove(fromPosition: Int, toPosition: Int) {
        viewModel.moveRepository(fromPosition, toPosition)
    }

    private fun onItemSwipe(position: Int) {
        viewModel.removeRepository(position)
    }

    companion object {
        private const val TAG = "RepositoryFragment"
    }
}