package com.bobrbolt.gettogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bobrbolt.gettogether.mainFragments.LoginFragment
import com.bobrbolt.gettogether.mainFragments.MapsFragment
import com.bobrbolt.gettogether.mainFragments.FeedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.visibility = View.GONE

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainLayout, LoginFragment(applicationContext))
            .commit()

//        setFragment(FeedFragment.newInstance())
        bottomNav.setOnItemSelectedListener {
            if (it.itemId == R.id.feedButton)
                setFragment(FeedFragment.newInstance())
            else
                setFragment(MapsFragment.newInstance())
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }
}