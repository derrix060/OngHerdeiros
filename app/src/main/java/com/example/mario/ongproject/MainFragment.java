package com.example.mario.ongproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mario on 05/05/17.
 */

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.activity_main, container, false);
    }

    public View changeView(LayoutInflater inflater, ViewGroup container, int view){

        return inflater.inflate(view, container, false);
    }
}
