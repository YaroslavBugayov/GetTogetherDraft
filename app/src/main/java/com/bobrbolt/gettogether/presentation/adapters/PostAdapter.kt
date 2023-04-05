package com.bobrbolt.gettogether.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.PostItemBinding
import com.bobrbolt.gettogether.presentation.models.PostModel

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var postList = ArrayList<PostModel>()

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PostItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(post: PostModel, context: Context) = with(binding) {
            postUsernameTextView.text = post.authorUsername
            postTaggedTextView.text =
                "${context.getString(R.string.tagged)}: " +
                        post.tagged.joinToString { elem -> "$elem " }.trim()
            postDescriptionTextView.text = post.description
            val adapter = PostViewPagerAdapter()
            adapter.setImageList(post.images)
            postViewPager.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position], holder.itemView.context)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<PostModel>) {
        postList = list
    }

    fun addPost(post: PostModel) {
        postList.add(post)
    }
}