package com.bobrbolt.gettogether.presentation.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean>
        get() = _isUserAuthenticated

    private val _mainLayoutFragment = MutableLiveData<Fragment>()
    val mainLayoutFragment: LiveData<Fragment>
        get() = _mainLayoutFragment

    private val auth = FirebaseAuth.getInstance()

    fun checkIsUserAuthenticated() {
        _isUserAuthenticated.value = auth.currentUser != null
    }

    fun setMainLayoutFragment(fragment: Fragment) {
        _mainLayoutFragment.value = fragment
    }

}