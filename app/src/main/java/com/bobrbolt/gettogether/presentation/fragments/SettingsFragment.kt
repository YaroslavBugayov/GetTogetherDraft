package com.bobrbolt.gettogether.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentSettingsBinding
import com.bobrbolt.gettogether.presentation.fragments.loginFragments.AuthFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.buttonLogOut.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainLayout, AuthFragment())
                .commit()
        }
        Firebase.auth.signOut()
        return binding.root
    }

}