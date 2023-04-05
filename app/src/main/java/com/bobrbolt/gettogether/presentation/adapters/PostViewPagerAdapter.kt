package com.bobrbolt.gettogether.presentation.adapters

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.PostImageItemBinding

class PostViewPagerAdapter: RecyclerView.Adapter<PostViewPagerAdapter.PostViewPagerHolder>() {

    private var imageList = ArrayList<Bitmap>()

    class PostViewPagerHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PostImageItemBinding.bind(view)

        fun bind(image: Bitmap) = with(binding) {
            imageView.setImageBitmap(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_image_item, parent, false)
        return PostViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: PostViewPagerHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun setImageList(list: ArrayList<Bitmap>) {
        imageList = list
    }
}
