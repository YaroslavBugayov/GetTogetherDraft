package com.bobrbolt.gettogether.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            images = arrayListOf("", ""),
            description = "description1",
            tagged = arrayListOf("tag1", "tag2")
        ))

        adapter.addPost(PostModel(
            authorUsername = "username2",
            images = arrayListOf("", ""),
            description = "description2",
            tagged = arrayListOf("tag3", "tag4")
        ))

        adapter.addPost(PostModel(
            authorUsername = "username3",
            images = arrayListOf("", ""),
            description = "description3",
            tagged = arrayListOf("tag3", "tag4")
        ))

        adapter.addPost(PostModel(
            authorUsername = "username4",
            images = arrayListOf("", ""),
            description = "description4",
            tagged = arrayListOf("tag3", "tag4")
        ))

        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}