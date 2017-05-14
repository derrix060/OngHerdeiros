package com.example.mario.ongproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mario on 05/05/17.
 */

public class EventsFragment extends Fragment {

    CaldroidFragment caldroidFragment; // Source: https://github.com/roomorama/Caldroid

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.events_view, container, false);


        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.customCalendar, caldroidFragment);
        t.commit();

        populateCalendar();

        return v;
    }

    public void populateCalendar(){
        ColorDrawable green = new ColorDrawable(Color.GRAY);
        Calendar myCal = Calendar.getInstance();

        myCal.set(Calendar.YEAR, 2017);
        myCal.set(Calendar.MONTH, 4);
        myCal.set(Calendar.DAY_OF_MONTH, 20);
        Date dt = myCal.getTime();

        caldroidFragment.setBackgroundDrawableForDate(green, dt);

        myCal.set(Calendar.YEAR, 2017);
        myCal.set(Calendar.MONTH, 4);
        myCal.set(Calendar.DAY_OF_MONTH, 22);
        dt = myCal.getTime();

        caldroidFragment.setBackgroundDrawableForDate(green, dt);

        /*
        05/20/2017 - Pizza Night
        05/22/2017 - Bingo Night


         */


        caldroidFragment.refreshView();
    }
}
