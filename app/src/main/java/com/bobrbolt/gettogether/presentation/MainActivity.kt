package com.bobrbolt.gettogether.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.databinding.ActivityMainBinding
import com.bobrbolt.gettogether.loginDb.AccountDatabase
import com.bobrbolt.gettogether.presentation.loginFragments.LoginFragment
import com.bobrbolt.gettogether.presentation.fragments.MapsFragment
import com.bobrbolt.gettogether.presentation.fragments.FeedFragment
import com.bobrbolt.gettogether.presentation.loginFragments.LoadingFragment
import com.bobrbolt.gettogether.presentation.fragments.NotificationsFragment
import com.bobrbolt.gettogether.presentation.fragments.ProfileFragment
import com.bobrbolt.gettogether.presentation.viewModels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url = "http://10.0.2.2:8080/token"
    private lateinit var requestQueue: RequestQueue
    private lateinit var bottomNav: BottomNavigationView
    private val loadingFragment = LoadingFragment.newInstance()

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[MainViewModel::class.java]

        bottomNav = binding.bottomNav
        bottomNav.visibility = View.GONE

//        openLoadingScreen()

//        isLoggedIn()

        // Plug
        setFragment(FeedFragment.newInstance())
        bottomNav.visibility = View.VISIBLE

        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.feedButton -> setFragment(FeedFragment.newInstance())
                R.id.mapButton -> setFragment(MapsFragment.newInstance())
                R.id.notificationsButton -> setFragment(NotificationsFragment.newInstance())
                R.id.profileButton -> setFragment(ProfileFragment.newInstance())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun isLoggedIn() {
        Thread {
            requestQueue = Volley.newRequestQueue(applicationContext)
            val database = AccountDatabase.getDatabase(applicationContext)
            if (database.accountDao().getCount() == 0) {
                openLoginFragment()
            } else {
                val login = database.accountDao().getAllAccounts().last().login
                val token = database.accountDao().getAllAccounts().last().token
                Log.d("test", "$login $token")
                val authJson = JSONObject("{\"login\":\"${login}\", \"token\":\"${token}\"}")
                val request = object : JsonObjectRequest(
                    Method.POST,
                    url,
                    authJson,
                    {
                        runOnUiThread {
                            supportFragmentManager
                                .beginTransaction()
                                .remove(loadingFragment)
                                .commit()
                            setFragment(FeedFragment.newInstance())
                            bottomNav.visibility = View.VISIBLE
                        }
                    },
                    {
                        openLoginFragment()
                    }
                ) {}
                requestQueue.add(request)
            }
        }.start()
    }

    private fun openLoginFragment() {
        runOnUiThread {
            bottomNav.visibility = View.GONE

            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainLayout.id, LoginFragment(applicationContext))
                .commit()
        }
    }

    private fun openLoadingScreen() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.mainLayout.id, loadingFragment)
            .commit()
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.placeholder.id, fragment)
            .commit()
    }
}