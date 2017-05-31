package com.example.mario.ongproject.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mario.ongproject.R;

public class MainActivity extends AppCompatActivity {

        private Fragment fragment;
        private FragmentManager fragmentManager;
        BottomNavigationView bottomNavigationView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            fragmentManager = getSupportFragmentManager();
            fragment = new HomeFragment();

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.main_container, fragment).commit();


            // Navigation
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.navigation_help:
                            fragment = new DetachFragment();
                            break;

                        case R.id.navigation_donate:
                            fragment = new DonateFragment();
                            break;

                        case R.id.navigation_contact:
                            fragment = new ContactFragment();
                            break;

                        case R.id.navigation_events:
                            fragment = new EventsFragment();
                            break;
                    }

                    fragmentTransaction.replace(R.id.main_container, fragment).commit();
                    return true;
                }
            });
        }

    }