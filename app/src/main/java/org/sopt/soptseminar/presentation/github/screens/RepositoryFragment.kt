package org.sopt.soptseminar.presentation.github.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptseminar.databinding.FragmentRepositoryBinding
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.presentation.github.adapters.RepositoryListAdapter
import org.sopt.soptseminar.presentation.home.ProfileViewModel
import org.sopt.soptseminar.util.ItemTouchHelperCallback

@AndroidEntryPoint
class RepositoryFragment : Fragment(), RepositoryListAdapter.OnItemClickListener,
    RepositoryListAdapter.OnItemTouchListener {
    private lateinit var binding: FragmentRepositoryBinding
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var adapter: RepositoryListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initLayout()
        addListeners()

        return binding.root
    }

    private fun initLayout() {
        adapter = RepositoryListAdapter().apply {
            binding.repositoryList.adapter = this
            setOnItemClickListener(this@RepositoryFragment)
            setOnItemTouchListener(this@RepositoryFragment)
            ItemTouchHelper(ItemTouchHelperCallback(this)).attachToRecyclerView(binding.repositoryList)
        }
    }

    private fun addListeners() {
        viewModel.getRepositories().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            adapter.submitList(it.toMutableList())
        }
    }

    override fun onItemClick(item: RepositoryInfo) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        viewModel.moveRepository(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        viewModel.removeRepository(position)
    }

    companion object {
        private const val TAG = "RepositoryFragment"
    }
}