package com.bobrbolt.gettogether

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bobrbolt.gettogether.mainFragments.FeedFragment
import com.bobrbolt.gettogether.mainFragments.MapsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(FeedFragment.newInstance())
        findViewById<BottomNavigationView>(R.id.bottomNav).setOnItemSelectedListener {
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