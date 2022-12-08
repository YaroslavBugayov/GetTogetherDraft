package com.bobrbolt.gettogether.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bobrbolt.gettogether.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_settings, container, false)
        inf.findViewById<Button>(R.id.buttonLogOut).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .remove(this)
                .add(R.id.mainLayout, LoginFragment(requireContext()))
                .commit()
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
        }
        return inf
    }

}