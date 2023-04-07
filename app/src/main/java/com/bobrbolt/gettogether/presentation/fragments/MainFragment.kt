package com.bobrbolt.gettogether.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentMainBinding
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        bottomNav = binding.bottomNav

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.placeholderFragment.observe(viewLifecycleOwner) { fragment ->
            openOnPlaceholder(fragment)
        }

        viewModel.setPlaceholderFragment(FeedFragment.newInstance())
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.feedButton -> viewModel.setPlaceholderFragment(FeedFragment.newInstance())
                R.id.mapButton -> viewModel.setPlaceholderFragment(MapsFragment.newInstance())
                R.id.notificationsButton -> viewModel.setPlaceholderFragment(NotificationsFragment.newInstance())
                R.id.profileButton -> viewModel.setPlaceholderFragment(ProfileFragment.newInstance())
            }
            return@setOnItemSelectedListener true
        }

        return binding.root
    }

    private fun openOnPlaceholder(fragment: Fragment){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(binding.placeholder.id, fragment)
            .commit()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}