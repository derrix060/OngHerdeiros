package com.example.mario.ongproject.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mario.ongproject.R;
import com.example.mario.ongproject.controller.EventItemAdapter;
import com.example.mario.ongproject.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mario on 11/07/2017.
 */

public class EventsFragment extends Fragment {

    private ArrayList<Event> events = new ArrayList<>();
    private RecyclerView mRV;
    private EventItemAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.events_view, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.event_progressBar);

        mRV = (RecyclerView) v.findViewById(R.id.events_rv);
        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRV.setHasFixedSize(true);


        // use a linear layout manager
        mRV.setLayoutManager(new LinearLayoutManager(v.getContext()));

        // Custom adapter
        adapter = new EventItemAdapter(events);
        mRV.setAdapter(adapter);

        // Populate
        new GetEventsTask(v).execute();


        return v;
    }


    private class GetEventsTask extends AsyncTask<Void, Void, ArrayList<Event>> {

        View rootView;

        GetEventsTask(View rootView){
            this.rootView = rootView;
        }

        protected ArrayList<Event> doInBackground(Void... v){

            try {
                URL url = new URL("https://dry-anchorage-70819.herokuapp.com/api/eventos/");

                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();
                    if (response == HttpURLConnection.HTTP_OK) {
                        StringBuilder builder = new StringBuilder();
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                builder.append(line);
                            }
                        } catch (IOException e) {
                            Snackbar.make(rootView, getString(R.string.read_error), Snackbar.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        return convertJSONToArrayList(new JSONArray(builder.toString()));
                    }
                } catch (Exception e) {
                    Snackbar.make(rootView, getString(R.string.connect_error), Snackbar.LENGTH_LONG).show();
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                return new ArrayList<>();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return new ArrayList<>();

        }

        public ArrayList<Event> convertJSONToArrayList (JSONArray eventsJSON){

            ArrayList<Event> events = new ArrayList<>();

            try{
                for (int i = 0; i < eventsJSON.length(); i++){
                    JSONObject event = eventsJSON.getJSONObject(i);

                    String date = event.getString("date_date");
                    String description = event.getString("description_text");
                    String place = event.getString("local_text");
                    String title = event.getString("titulo_text");

                    String image_src = "";
                    if (!event.isNull("image_src_text"))
                        image_src = event.getString("image_src_text");

                    //String title, String date, String time, String place, String description, Bitmap img_src
                    events.add(new Event(title, date, place, description, image_src));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            return events;
        }

        protected void onPostExecute(ArrayList<Event> events){
            progressBar.setVisibility(View.INVISIBLE);
            if (events.size() == 0){
                Snackbar.make(rootView, getString(R.string.dont_find_event), Snackbar.LENGTH_LONG).show();
            }
            else{
                EventsFragment.this.events = events;
                adapter = new EventItemAdapter(EventsFragment.this.events);
                mRV.setAdapter(adapter);
                mRV.smoothScrollToPosition(0);
            }
        }
    }

}
