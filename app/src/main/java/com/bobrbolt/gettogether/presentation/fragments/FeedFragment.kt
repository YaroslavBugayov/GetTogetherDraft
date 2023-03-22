package com.bobrbolt.gettogether.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentFeedBinding
import com.bobrbolt.gettogether.presentation.adapters.PostAdapter
import com.bobrbolt.gettogether.presentation.models.PostModel

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.profileButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeholder, SettingsFragment())
                .commit()
        }

        recyclerView = binding.postsRecycleView
        recyclerView.adapter = adapter


        adapter.addPost(PostModel(
            authorUsername = "username1",
            images = listOf("", ""),
            description = "description1",
            tagged = listOf("tag1", "tag2")
        ))

        adapter.addPost(PostModel(
            authorUsername = "username2",
            images = listOf("", ""),
            description = "description2",
            tagged = listOf("tag3", "tag4")
        ))
        Log.d("bindTest", adapter.itemCount.toString())

        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}