package com.bobrbolt.gettogether.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bobrbolt.gettogether.presentation.viewModels.loginViewModels.AuthViewModel
import com.bobrbolt.gettogether.presentation.viewModels.loginViewModels.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewModelFactory(private val auth: FirebaseAuth) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(auth) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(auth) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}