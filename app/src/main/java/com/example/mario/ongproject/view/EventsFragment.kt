package com.example.mario.ongproject.view

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.mario.ongproject.R
import com.example.mario.ongproject.controller.EventItemAdapter
import com.example.mario.ongproject.model.Event
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Created by mario on 11/07/2017.
 */

class EventsFragment : Fragment() {

    private var events = ArrayList<Event>()
    private var mRV: RecyclerView? = null
    private var adapter: EventItemAdapter? = null
    private var progressBar: ProgressBar? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.events_view, container, false)

        progressBar = v.findViewById(R.id.event_progressBar) as ProgressBar

        mRV = v.findViewById(R.id.events_rv) as RecyclerView
        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRV!!.setHasFixedSize(true)


        // use a linear layout manager
        mRV!!.layoutManager = LinearLayoutManager(v.context)

        // Custom adapter
        adapter = EventItemAdapter(events)
        mRV!!.adapter = adapter

        // Populate
        GetEventsTask(v).execute()


        return v
    }


    private inner class GetEventsTask internal constructor(internal var rootView: View) : AsyncTask<Void, Void, ArrayList<Event>>() {

        override fun doInBackground(vararg v: Void): ArrayList<Event> {

            try {
                val url = URL("https://dry-anchorage-70819.herokuapp.com/api/eventos/")

                var connection: HttpURLConnection? = null
                try {
                    connection = url.openConnection() as HttpURLConnection
                    val response = connection.responseCode
                    if (response == HttpURLConnection.HTTP_OK) {
                        val builder = StringBuilder()
                        try {
                            BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                                for (line in reader.readLine()){
                                    builder.append(line)
                                }
                            }
                        } catch (e: IOException) {
                            Snackbar.make(rootView, getString(R.string.read_error), Snackbar.LENGTH_LONG).show()
                            e.printStackTrace()
                        }

                        return convertJSONToArrayList(JSONArray(builder.toString()))
                    }
                } catch (e: Exception) {
                    Snackbar.make(rootView, getString(R.string.connect_error), Snackbar.LENGTH_LONG).show()
                    e.printStackTrace()
                } finally {
                    if (connection != null) {
                        connection.disconnect()
                    }
                }
                return ArrayList()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            return ArrayList()

        }

        fun convertJSONToArrayList(eventsJSON: JSONArray): ArrayList<Event> {

            val events = ArrayList<Event>()

            try {
                for (i in 0..eventsJSON.length() - 1) {
                    val event = eventsJSON.getJSONObject(i)

                    val date = event.getString("date_date")
                    val description = event.getString("description_text")
                    val place = event.getString("local_text")
                    val title = event.getString("titulo_text")

                    var image_src = ""
                    if (!event.isNull("image_src_text"))
                        image_src = event.getString("image_src_text")

                    //String title, String date, String time, String place, String description, Bitmap img_src
                    events.add(Event(title, date, place, description, image_src))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return events
        }

        override fun onPostExecute(events: ArrayList<Event>) {
            progressBar!!.visibility = View.INVISIBLE
            if (events.size == 0) {
                Snackbar.make(rootView, getString(R.string.dont_find_event), Snackbar.LENGTH_LONG).show()
            } else {
                this@EventsFragment.events = events
                adapter = EventItemAdapter(this@EventsFragment.events)
                mRV!!.adapter = adapter
                mRV!!.smoothScrollToPosition(0)
            }
        }
    }

}
