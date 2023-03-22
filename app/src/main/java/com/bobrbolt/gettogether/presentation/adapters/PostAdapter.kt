package com.bobrbolt.gettogether.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.PostItemBinding
import com.bobrbolt.gettogether.presentation.fragments.items.PostImageFragment
import com.bobrbolt.gettogether.presentation.models.PostModel

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var postList = ArrayList<PostModel>()

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PostItemBinding.bind(view)
        fun bind(post: PostModel) = with(binding) {
            Log.d("bindTest", post.authorUsername)
            postUsernameTextView.text = post.authorUsername
            postTaggedTextView.text = "Tagged: " + post.tagged.toString()
            postDescriptionTextView.text = post.description
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
//        holder.itemView.findViewById<TextView>(R.id.postUsernameTextView).text =
//            postList[position].authorUsername
//        holder.itemView.findViewById<TextView>(R.id.postTaggedTextView).text =
//            "Tagged: " + postList[position].tagged.toString()
//        holder.itemView.findViewById<TextView>(R.id.postDescriptionTextView).text =
//            postList[position].description
//        holder.itemView.findViewById<ViewPager2>(R.id.postViewPager).adapter =
//            PostViewPagerAdapter(holder.itemView.parent, postList[position].images.toList())
        holder.bind(postList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<PostModel>) {
        postList = list
        notifyDataSetChanged()
    }

    fun addPost(post: PostModel) {
        postList.add(post)
    }
}