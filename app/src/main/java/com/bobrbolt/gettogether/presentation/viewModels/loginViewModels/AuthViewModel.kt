package com.bobrbolt.gettogether.presentation.viewModels.loginViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(private val auth: FirebaseAuth) : ViewModel() {

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean>
        get() = _isUserAuthenticated

    fun authenticateUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserAuthenticated.value = true
                } else {
                    Log.d("Auth", "sign in failure")
                }
            }
    }
}