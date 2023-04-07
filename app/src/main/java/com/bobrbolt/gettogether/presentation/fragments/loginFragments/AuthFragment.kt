package com.bobrbolt.gettogether.presentation.fragments.loginFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentLoginBinding
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel
import com.bobrbolt.gettogether.presentation.viewModels.loginViewModels.AuthViewModel
import com.bobrbolt.gettogether.presentation.viewModels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthFragment() : Fragment() {

//    val url = "http://10.0.2.2:8080/login"
//    private val requestQueue = Volley.newRequestQueue(applicationContext)

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var activityViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(FirebaseAuth.getInstance()))[AuthViewModel::class.java]
        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        with(binding) {
            buttonSignIn.setOnClickListener { authenticateUser() }
            buttonSignUp.setOnClickListener { signUp() }
        }

        auth = Firebase.auth

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

    }

    private fun authenticateUser() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        viewModel.authenticateUser(email, password)
        viewModel.isUserAuthenticated.observe(viewLifecycleOwner) { isUserAuthenticate ->
            if (isUserAuthenticate) {
                activityViewModel.checkIsUserAuthenticated()
            }
        }
    }

    private fun signUp() {
        activityViewModel.setMainLayoutFragment(SignUpFragment())
    }
}