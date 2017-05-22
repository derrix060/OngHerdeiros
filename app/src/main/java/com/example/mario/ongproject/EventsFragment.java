package com.example.mario.ongproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mario on 05/05/17.
 */

public class EventsFragment extends Fragment {

    ArrayList<Calendar> dtEventos;
    ArrayList<String> descrEventos;

    CaldroidFragment caldroidFragment; // Source: https://github.com/roomorama/Caldroid

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.events_view, container, false);

        // Tuned calendar
        caldroidFragment = new CaldroidFragment();

        // Populate calendar
        populateCalendar();

        // Get actual date
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        // Show calendar on screen
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.customCalendar, caldroidFragment);
        t.commit();


        // To show event on click
        final CaldroidListener listener = new CaldroidListener(){
            @Override
            public void onSelectDate(Date date, View view) {
                int idx = idxEventDate(date);
                if(idx != -1)
                    Snackbar.make(view, descrEventos.get(idx), Snackbar.LENGTH_SHORT).show();
            }

        };
        caldroidFragment.setCaldroidListener(listener);

        return v;
    }

    private int idxEventDate(Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        for(int i = 0; i<dtEventos.size(); i++){
            Calendar c = dtEventos.get(i);
            if (c.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)
                    && c.get(Calendar.YEAR) == cal.get(Calendar.YEAR))
                return i;
        }

        return -1;
    }

    public void populateCalendar(){
        dtEventos = new ArrayList<>();
        descrEventos = new ArrayList<>();
        ColorDrawable green = new ColorDrawable(Color.GRAY);
        Calendar myCal = Calendar.getInstance();

        myCal.set(Calendar.YEAR, 2017);
        myCal.set(Calendar.MONTH, 4);
        myCal.set(Calendar.DAY_OF_MONTH, 20);
        Date dt = myCal.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        dtEventos.add(cal);
        descrEventos.add(new String(getString(R.string.events_first_event)));

        caldroidFragment.setBackgroundDrawableForDate(green, dt);

        myCal.set(Calendar.YEAR, 2017);
        myCal.set(Calendar.MONTH, 4);
        myCal.set(Calendar.DAY_OF_MONTH, 21);
        dt = myCal.getTime();
        cal = Calendar.getInstance();
        cal.setTime(dt);
        dtEventos.add(cal);
        descrEventos.add(new String(getString(R.string.events_seccond_event)));

        caldroidFragment.setBackgroundDrawableForDate(green, dt);
        caldroidFragment.refreshView();
    }
}
