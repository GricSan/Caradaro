package com.gricsan.caradaro.features.user_list.presentation

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gricsan.caradaro.R
import com.gricsan.caradaro.base.utils.dp
import com.gricsan.caradaro.base.utils.loadImageFromUrl
import com.gricsan.caradaro.databinding.VhUserListItemBinding
import com.gricsan.caradaro.features.user_list.domain.models.User

class UserListAdapter(
    private val onItemClicked: ((User) -> Unit)? = null
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val itemImageTransformationOptions = RequestOptions().transform(
        CenterCrop(),
        RoundedCorners(6.dp)
    )

    private var data: List<User> = mutableListOf()


    // Adapter's item decoration
    val itemDecoration = object : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            var topOffset = 0.dp
            if (parent.getChildAdapterPosition(view) == 0) {
                topOffset = 8.dp
            }

            outRect.apply { left = 8.dp; top = topOffset; right = 8.dp; bottom = 8.dp }
        }

    }


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
                tvUserName.text =
                    root.resources.getString(R.string.user_list_vh_user_name, info.name)
                tvUserSurname.text =
                    root.resources.getString(R.string.user_list_vh_user_surname, info.surname)
                ivUserPhoto.loadImageFromUrl(
                    url = info.photoUrl,
                    thumbnailResId = R.drawable.img_user_photo_placeholder,
                    options = itemImageTransformationOptions
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