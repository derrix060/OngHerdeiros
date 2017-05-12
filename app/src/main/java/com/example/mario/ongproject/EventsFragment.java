package com.example.mario.ongproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by mario on 05/05/17.
 */

public class EventsFragment extends Fragment {
    CalendarView calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.events_view, container, false);

        calendar = (CalendarView) v.findViewById(R.id.calendarView);

        return v;
    }

    public void populateCalendar(){

    }
}
