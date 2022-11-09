package com.gricsan.caradaro.features.user_list.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gricsan.caradaro.R
import com.gricsan.caradaro.databinding.VhUserListItemBinding
import com.gricsan.caradaro.features.user_list.domain.entities.User
import com.gricsan.caradaro.features.user_list.domain.entities.UserInfo

class UserListAdapter(
    private val onItemClicked: ((User) -> Unit)? = null
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var data: List<User> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<User>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VhUserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].info?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.setupListeners()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.clearListeners()
    }


    // ViewHolder class of this RecyclerView Adapter
    inner class ViewHolder(
        private val binding: VhUserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(info: UserInfo) {
            binding.apply {
                tvUserName.text = info.name
                tvUserSurname.text = info.surname
                Glide.with(ivUserPhoto.context)
                    .load(info.photoUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_photo_placeholder)
                    .into(ivUserPhoto)
            }
        }

        fun setupListeners() {
            onItemClicked?.let { onItemClicked ->
                val user = data[adapterPosition]
                binding.root.setOnClickListener { onItemClicked(user) }
            }
        }

        fun clearListeners() {
            binding.root.setOnClickListener(null)
        }

    }

}