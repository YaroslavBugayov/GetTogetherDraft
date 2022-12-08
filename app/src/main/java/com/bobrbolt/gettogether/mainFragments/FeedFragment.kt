package com.bobrbolt.gettogether.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.bobrbolt.gettogether.R

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_feed, container, false)
        inf.findViewById<ImageButton>(R.id.profileButton).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeholder, SettingsFragment())
                .commit()
        }
        return inf
    }

    companion object{
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}