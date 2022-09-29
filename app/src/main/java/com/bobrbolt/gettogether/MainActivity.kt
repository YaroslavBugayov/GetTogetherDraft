package com.bobrbolt.gettogether

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import com.bobrbolt.gettogether.mainFragments.FeedFragment
import com.bobrbolt.gettogether.mainFragments.MapsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, FeedFragment.newInstance())
            .commit()

        // доступ:
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_REQUEST_CODE)
//            return
//        }
    }
    
    fun clickFeed(item: MenuItem){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, FeedFragment.newInstance())
            .commit()
        findViewById<BottomNavigationView>(R.id.bottomNav).selectedItemId = R.id.feedButton
    }
    fun clickMap(item: MenuItem){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, MapsFragment.newInstance())
            .commit()
        findViewById<BottomNavigationView>(R.id.bottomNav).selectedItemId = R.id.mapButton
    }
}