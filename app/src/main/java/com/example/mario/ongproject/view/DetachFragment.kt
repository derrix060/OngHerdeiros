package com.example.mario.ongproject.view

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.mario.ongproject.R
import com.example.mario.ongproject.controller.DetachItemAdapter
import com.example.mario.ongproject.model.DonateItem
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Created by mario on 05/05/17.
 */

class DetachFragment : Fragment() {
    private var myItems = ArrayList<DonateItem>()
    private var adapter: DetachItemAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.detach_view, container, false)

        mRecyclerView = v.findViewById(R.id.detach_rv) as RecyclerView

        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)


        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = mLayoutManager

        progressBar = v.findViewById(R.id.progress_bar) as ProgressBar

        // Get items from backend
        GetItemsTask().execute()

        return v
    }


    private inner class GetItemsTask : AsyncTask<Void, Void, ArrayList<DonateItem>>() {
        override fun doInBackground(vararg movies: Void): ArrayList<DonateItem> {
            var connection: HttpURLConnection? = null
            try {
                val url = URL("https://dry-anchorage-70819.herokuapp.com/api/donateitens/")


                connection = url.openConnection() as HttpURLConnection
                val response = connection.responseCode
                if (response == HttpURLConnection.HTTP_OK) {
                    val builder = StringBuilder()
                    try {
                        BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                            for (line in reader.readLines())
                             {
                                builder.append(line)
                            }
                            return convertJSONToArrayList(JSONArray(builder.toString()))
                        }
                    } catch (e: IOException) {
                        Snackbar.make(view!!, getString(R.string.read_error), Snackbar.LENGTH_LONG).show()
                        e.printStackTrace()
                    } catch (e: Exception) {
                        Log.d(TAG, "doInBackground: Exception-> " + e.toString())
                    }

                }
            } catch (e: Exception) {
                Snackbar.make(view!!, getString(R.string.connect_error), Snackbar.LENGTH_LONG).show()
                e.printStackTrace()
            } finally {
                if (connection != null) {
                    connection.disconnect()
                }
            }
            return ArrayList()
        }

        override fun onPostExecute(itens: ArrayList<DonateItem>) {
            Log.d(TAG, "onPostExecute: itens.size: " + itens.size)
            if (itens.size == 0)
                Snackbar.make(view!!, getString(R.string.no_detach_items), Snackbar.LENGTH_LONG).show()
            else {
                myItems = itens
            }

            // Remove progressBar
            progressBar!!.visibility = View.INVISIBLE

            // Custom adapter
            adapter = DetachItemAdapter(myItems)
            mRecyclerView!!.adapter = adapter

        }

        fun convertJSONToArrayList(items: JSONArray): ArrayList<DonateItem> {

            val movieList = ArrayList<DonateItem>()

            try {
                for (i in 0 until items.length()) {
                    val item = items.getJSONObject(i)
                    val name = item.getString("name_text")
                    val imagePath = item.getString("image_src_text")

                    movieList.add(DonateItem(imagePath, name))

                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return movieList
        }
    }

    companion object {

        private val TAG = "DetachFragment"
    }
}// Required empty public constructor
