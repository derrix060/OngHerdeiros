package com.example.mario.ongproject.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mario.ongproject.R;

/**
 * Created by mario on 11/07/2017.
 */

public class EventDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_details_view);

        //Toolbar



        TextView title = (TextView) findViewById(R.id.event_dialog_title);
        TextView date_time = (TextView) findViewById(R.id.event_dialog_date_time);
        TextView place = (TextView) findViewById(R.id.event_dialog_place);
        TextView descripton = (TextView) findViewById(R.id.event_dialog_description);

        title.setText(getIntent().getStringExtra("EVENT_TITLE"));
        date_time.setText(getIntent().getStringExtra("EVENT_DATE") + " - " + getIntent().getStringExtra("EVENT_TIME"));
        place.setText(getIntent().getStringExtra("EVENT_PLACE"));
        descripton.setText(getIntent().getStringExtra("EVENT_DESCRIPTION"));

    }

}
