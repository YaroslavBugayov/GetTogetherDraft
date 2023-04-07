package com.bobrbolt.gettogether.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentFeedBinding
import com.bobrbolt.gettogether.presentation.adapters.PostAdapter
import com.bobrbolt.gettogether.presentation.viewModels.FeedViewModel
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private lateinit var viewModel: FeedViewModel
    private lateinit var activityViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Binding
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recycler view
        recyclerView = binding.postsRecycleView
        adapter = PostAdapter()
        recyclerView.adapter = adapter

        // View model
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.posts.observe(viewLifecycleOwner) { posts -> adapter.setList(posts) }

//        adapter.setList(arrayListOf(PostModel(
//            authorUsername = "username1",
//            images = arrayListOf(image, image),
//            description = "description1",
//            tagged = arrayListOf("tag1", "tag2")
//        )))

        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.profileButton.setOnClickListener {
            activityViewModel.setMainLayoutFragment(SettingsFragment())
        }

        return view
    }


    companion object{
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}