package org.sopt.soptseminar.presentation.github.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.soptseminar.databinding.ItemFollowerBinding
import org.sopt.soptseminar.models.FollowerInfo

class FollowerListAdapter(private val onItemClick: (FollowerInfo) -> Unit) :
    ListAdapter<FollowerInfo, RecyclerView.ViewHolder>(diffCallback) {

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerInfo, onItemClick: (FollowerInfo) -> Unit) {
            with(binding) {
                this.follower = follower

                Glide.with(profileImage).load(follower.profile).into(profileImage)
                profileImage.clipToOutline = true

                profileImage.setOnClickListener {
                    onItemClick(follower)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFollowerBinding.inflate(
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

    companion object {
        private const val TAG = "FollowerListAdapter"

        private val diffCallback = object : DiffUtil.ItemCallback<FollowerInfo>() {
            override fun areItemsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}