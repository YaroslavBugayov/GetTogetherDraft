package com.bobrbolt.gettogether.presentation.viewModels.loginViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel(private val auth: FirebaseAuth) : ViewModel() {
    private val _isUserRegistered = MutableLiveData<Boolean>()
    val isUserRegistered: LiveData<Boolean>
        get() = _isUserRegistered

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserRegistered.value = true
                } else {
                    Log.d("Auth", "sign up failure")
                }
            }
    }
}