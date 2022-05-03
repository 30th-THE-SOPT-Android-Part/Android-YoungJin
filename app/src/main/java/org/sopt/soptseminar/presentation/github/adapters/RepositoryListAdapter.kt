package org.sopt.soptseminar.presentation.github.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptseminar.databinding.ItemRepositoryBinding
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.util.ItemTouchHelperListener

class RepositoryListAdapter(
    private val onItemClick: (RepositoryInfo) -> Unit,
    private val _onItemMove: (Int, Int) -> Unit,
    private val _onItemSwipe: (Int) -> Unit
) : ListAdapter<RepositoryInfo, RecyclerView.ViewHolder>(diffCallback),
    ItemTouchHelperListener {

    class FollowerViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryInfo, onItemClick: (RepositoryInfo) -> Unit) {
            with(binding) {
                this.repository = repository
                repositoryContainer.setOnClickListener {
                    onItemClick(repository)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (viewHolder) {
            is FollowerViewHolder -> viewHolder.bind(data, onItemClick)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        _onItemMove(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        _onItemSwipe(position)
    }

    companion object {
        private const val TAG = "RepositoryListAdapter"

        private val diffCallback = object : DiffUtil.ItemCallback<RepositoryInfo>() {
            override fun areItemsTheSame(
                oldItem: RepositoryInfo,
                newItem: RepositoryInfo
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RepositoryInfo,
                newItem: RepositoryInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}