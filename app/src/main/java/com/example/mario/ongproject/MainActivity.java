package com.example.mario.ongproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        private TextView mTextMessage;

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_help:
                        mTextMessage.setText(R.string.title_help);
                        return true;
                    case R.id.navigation_donate:
                        mTextMessage.setText(R.string.title_donate);
                        return true;
                    case R.id.navigation_contact:
                        mTextMessage.setText(R.string.title_contact);
                        return true;
                    case R.id.navigation_events:
                        mTextMessage.setText(R.string.title_events);
                        return true;
                }
                return false;
            }

        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

    }

    /*
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        //final FragmentManager fragmentManager = getSupportFragmentManager();

        /*
        final Fragment homeFragment = new HomeFragment();
        final Fragment patronizeFragment = new PatronizeFragment();
        final Fragment donateFragment = new DonateFragment();
        final Fragment contactFragment = new ContactFragment();
        final Fragment eventsFragment = new EventsFragment();


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //fragmentTransaction.replace(R.id.mainFragment, homeFragment).commit();
                    return true;
                case R.id.navigation_help:
                    //fragmentTransaction.replace(R.id.mainFragment, patronizeFragment).commit();
                    return true;
                case R.id.navigation_donate:
                    //fragmentTransaction.replace(R.id.mainFragment, donateFragment).commit();
                    return true;
                case R.id.navigation_contact:
                    //fragmentTransaction.replace(R.id.mainFragment, contactFragment).commit();
                    return true;
                case R.id.navigation_events:
                    //fragmentTransaction.replace(R.id.mainFragment, eventsFragment).commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
    */


