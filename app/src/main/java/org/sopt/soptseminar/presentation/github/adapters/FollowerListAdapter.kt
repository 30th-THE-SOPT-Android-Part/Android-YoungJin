package org.sopt.soptseminar.presentation.github.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.soptseminar.databinding.ItemFollowerBinding
import org.sopt.soptseminar.models.FollowerInfo

class FollowerListAdapter : ListAdapter<FollowerInfo, RecyclerView.ViewHolder>(diffCallback) {
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(item: FollowerInfo)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerInfo) {
            with(binding) {
                this.follower = follower

                Glide.with(profileImage).load(follower.profile).into(profileImage)
                profileImage.clipToOutline = true

                profileImage.setOnClickListener {
                    listener.onItemClick(follower)
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
            is FollowerViewHolder -> viewHolder.bind(data)
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