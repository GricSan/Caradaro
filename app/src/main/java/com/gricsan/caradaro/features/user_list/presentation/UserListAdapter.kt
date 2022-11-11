package com.gricsan.caradaro.features.user_list.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gricsan.caradaro.R
import com.gricsan.caradaro.base.utils.loadImageFromUrl
import com.gricsan.caradaro.databinding.VhUserListItemBinding
import com.gricsan.caradaro.features.user_list.domain.models.User

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
        holder.bind(data[position])
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

        fun bind(info: User) {
            binding.apply {
                tvUserName.text = info.name
                tvUserSurname.text = info.surname
                ivUserPhoto.loadImageFromUrl(
                    url = info.photoUrl,
                    placeholderResId = R.drawable.ic_user_photo_placeholder
                )
            }
        }

        fun setupListeners() {
            binding.root.setOnClickListener { onItemClicked?.invoke(data[adapterPosition]) }
        }

        fun clearListeners() {
            binding.root.setOnClickListener(null)
        }

    }

}