package com.example.mario.ongproject.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mario.ongproject.controller.DetachItemAdapter;
import com.example.mario.ongproject.model.DonateItem;
import com.example.mario.ongproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mario on 05/05/17.
 */

public class DetachFragment extends Fragment {

    private static final String TAG = "DetachFragment";
    private ArrayList<DonateItem> myItems = new ArrayList<>();
    private DetachItemAdapter adapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;

    public DetachFragment(){
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detach_view, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.detach_rv);

        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        // Get items from backend
        new GetItemsTask().execute();

        return v;
    }


    private class GetItemsTask extends AsyncTask<Void, Void, ArrayList<DonateItem>> {
        protected ArrayList<DonateItem> doInBackground(Void... movies) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://dry-anchorage-70819.herokuapp.com/api/donateitens/");


                connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    StringBuilder builder = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        return convertJSONToArrayList(new JSONArray(builder.toString()));
                    } catch (IOException e) {
                        Snackbar.make(getView(), getString(R.string.read_error), Snackbar.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (Exception e){
                        Log.d(TAG, "doInBackground: Exception-> " + e.toString());
                    }
                }
            } catch (Exception e) {
                Snackbar.make(getView(), getString(R.string.connect_error), Snackbar.LENGTH_LONG).show();
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return new ArrayList<>();
        }

        protected void onPostExecute(ArrayList<DonateItem> itens) {
            Log.d(TAG, "onPostExecute: itens.size: " + itens.size());
            if (itens.size() == 0)
                Snackbar.make(getView(), getString(R.string.no_detach_items),Snackbar.LENGTH_LONG).show();
            else {
                myItems = itens;
            }

            // Remove progressBar
            progressBar.setVisibility(View.INVISIBLE);

            // Custom adapter
            adapter = new DetachItemAdapter(myItems);
            mRecyclerView.setAdapter(adapter);

        }

        public ArrayList<DonateItem> convertJSONToArrayList (JSONArray items){

            ArrayList<DonateItem> movieList = new ArrayList<>();

            try{
                for (int i = 0; i < items.length(); i++){
                    JSONObject item = items.getJSONObject(i);
                    String name = item.getString("name_text");
                    String imagePath = item.getString("image_src_text");

                    movieList.add(new DonateItem(imagePath, name));

                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            return movieList;
        }
    }
}
