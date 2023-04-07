package com.bobrbolt.gettogether.presentation.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _placeholderFragment = MutableLiveData<Fragment>()
    val placeholderFragment: LiveData<Fragment>
        get() = _placeholderFragment

    fun setPlaceholderFragment(fragment: Fragment) {
        _placeholderFragment.value = fragment
    }
}