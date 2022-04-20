package org.sopt.soptseminar.presentation.github.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptseminar.databinding.ItemRepositoryBinding
import org.sopt.soptseminar.models.RepositoryInfo

class RepositoryListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataSet = arrayListOf<RepositoryInfo>()
    private lateinit var listener: OnItemClickListener

    private val diffCallback = object : DiffUtil.ItemCallback<RepositoryInfo>() {
        override fun areItemsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    interface OnItemClickListener {
        fun onItemClick(item: RepositoryInfo)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class FollowerViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryInfo) {
            with(binding) {
                this.item = repository
                repositoryContainer.setOnClickListener {
                    listener.onItemClick(repository)
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
        val data = differ.currentList[position]
        when (viewHolder) {
            is FollowerViewHolder -> viewHolder.bind(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(items: List<RepositoryInfo>?) {
        differ.submitList(items)
    }

    companion object {
        private const val TAG = "RepositoryListAdapter"
    }
}