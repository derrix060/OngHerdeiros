package com.example.mario.ongproject.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.mario.ongproject.R

class MainActivity : AppCompatActivity() {

    private var fragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private lateinit  var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        fragment = HomeFragment()

        val transaction = fragmentManager!!.beginTransaction()
        transaction.add(R.id.main_container, fragment).commit()


        // Navigation
        bottomNavigationView = findViewById(R.id.navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            when (item.itemId) {
                R.id.navigation_home -> fragment = HomeFragment()

                R.id.navigation_help -> fragment = DetachFragment()

                R.id.navigation_donate -> fragment = DonateFragment()

                R.id.navigation_contact -> fragment = ContactFragment()

                R.id.navigation_events -> fragment = EventsFragment()
            }

            fragmentTransaction.replace(R.id.main_container, fragment).commit()
            true
        }
    }

}