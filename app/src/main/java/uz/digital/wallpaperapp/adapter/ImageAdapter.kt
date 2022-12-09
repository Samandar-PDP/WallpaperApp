package uz.digital.wallpaperapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.digital.wallpaperapp.databinding.ImageLayoutBinding

class ImageAdapter: ListAdapter<Int, ImageAdapter.ImageViewHolder>(DiffCallBack()) {
    lateinit var onClick: (image: Int) -> Unit
    private class DiffCallBack: DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(private val binding: ImageLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageview.setImageResource(image)
            itemView.setOnClickListener {
                onClick.invoke(image)
            }
        }
    }
}