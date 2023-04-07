package com.bobrbolt.gettogether.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bobrbolt.gettogether.databinding.ActivityMainBinding
import com.bobrbolt.gettogether.presentation.fragments.*
import com.bobrbolt.gettogether.presentation.fragments.loginFragments.AuthFragment
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

//    val url = "http://10.0.2.2:8080/token"
//    private lateinit var requestQueue: RequestQueue

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observers
        viewModel.mainLayoutFragment.observe(this) { fragment ->
            openFragment(fragment)
        }
        viewModel.isUserAuthenticated.observe(this) { isLoggedIn ->
            viewModel.setMainLayoutFragment(
                if (isLoggedIn) MainFragment.newInstance() else AuthFragment()
            )
        }

        // Triggers
        viewModel.checkIsUserAuthenticated()
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainLayout.id, fragment)
            .commit()
    }
}