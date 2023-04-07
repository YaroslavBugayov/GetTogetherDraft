package com.bobrbolt.gettogether.presentation.fragments.loginFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.FragmentSignUpBinding
import com.bobrbolt.gettogether.presentation.fragments.FeedFragment
import com.bobrbolt.gettogether.presentation.fragments.MainFragment
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel
import com.bobrbolt.gettogether.presentation.viewModels.ViewModelFactory
import com.bobrbolt.gettogether.presentation.viewModels.loginViewModels.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment() : Fragment() {

//    val url = "http://10.0.2.2:8080/register"
//    private val requestQueue = Volley.newRequestQueue(applicationContext)

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var activityViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(FirebaseAuth.getInstance()))[SignUpViewModel::class.java]
        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        with(binding) {
            buttonSignUpRegister.setOnClickListener { signUp() }
            buttonBack.setOnClickListener { back() }
        }

        return binding.root
    }

    private fun signUp() {
        val username = binding.inputUsername.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        val passwordAgain = binding.inputPasswordAgain.text.toString()

        if (password == passwordAgain) {
            viewModel.registerUser(email, password)
        }
        viewModel.isUserRegistered.observe(viewLifecycleOwner) { isUserRegistered ->
            if (isUserRegistered) {
                activityViewModel.setMainLayoutFragment(MainFragment.newInstance())
            }
        }

    }

    private fun back() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainLayout, AuthFragment())
            .commit()
    }

//    private fun saveToken(login: String, token: String) {
//        Log.d("tag", "test")
//        val database = AccountDatabase.getDatabase(requireContext().applicationContext)
//        if (database.accountDao().getCount() == 0) {
//            database.accountDao().insert(Account(login = login, token = token))
//        } else {
//            database.accountDao().update(Account(login = login, token = token))
//        }
//    }

}