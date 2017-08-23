package com.example.mario.ongproject.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.mario.ongproject.R

/**
 * Created by mario on 11/07/2017.
 */

class EventDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.event_details_view)

        //Toolbar


        val title = findViewById(R.id.event_dialog_title) as TextView
        val date_time = findViewById(R.id.event_dialog_date_time) as TextView
        val place = findViewById(R.id.event_dialog_place) as TextView
        val descripton = findViewById(R.id.event_dialog_description) as TextView

        title.text = intent.getStringExtra("EVENT_TITLE")
        date_time.text = intent.getStringExtra("EVENT_DATE") + " - " + intent.getStringExtra("EVENT_TIME")
        place.text = intent.getStringExtra("EVENT_PLACE")
        descripton.text = intent.getStringExtra("EVENT_DESCRIPTION")

    }

}
