package com.bobrbolt.gettogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bobrbolt.gettogether.loginDb.Account
import com.bobrbolt.gettogether.loginDb.AccountDatabase
import com.bobrbolt.gettogether.mainFragments.LoginFragment
import com.bobrbolt.gettogether.mainFragments.MapsFragment
import com.bobrbolt.gettogether.mainFragments.FeedFragment
import com.bobrbolt.gettogether.mainFragments.LoadingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url = "http://10.0.2.2:8080/token"
    private lateinit var requestQueue: RequestQueue
    private lateinit var bottomNav: BottomNavigationView
    private val loadingFragment = LoadingFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.visibility = View.GONE

        openLoadingScreen()

        isLoggedIn()

        bottomNav.setOnItemSelectedListener {
            if (it.itemId == R.id.feedButton)
                setFragment(FeedFragment.newInstance())
            else
                setFragment(MapsFragment.newInstance())
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
                .replace(R.id.mainLayout, LoginFragment(applicationContext))
                .commit()
        }
    }

    private fun openLoadingScreen() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainLayout, loadingFragment)
            .commit()
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }
}