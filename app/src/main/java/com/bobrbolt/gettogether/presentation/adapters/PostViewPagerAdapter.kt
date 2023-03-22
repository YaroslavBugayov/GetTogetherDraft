package com.bobrbolt.gettogether.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobrbolt.gettogether.presentation.fragments.items.PostImageFragment

class PostViewPagerAdapter(fragment: FragmentActivity, private val imageList: List<String>):
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun createFragment(position: Int): Fragment {
        return PostImageFragment.newInstance(imageList[position])
    }
}