package com.example.mario.ongproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 05/05/17.
 */

public class DetachFragment extends Fragment {

    private DonateItem[] myItens;

    public DetachFragment(){
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize itens
        initializeData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detach_view, container, false);

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.detach_rv);

        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Custom adapter
        DetachItemAdapter adapter = new DetachItemAdapter(myItens);
        mRecyclerView.setAdapter(adapter);


        return v;
    }


    private void initializeData(){
        myItens = new DonateItem[5];
        myItens[0] = new DonateItem(R.drawable.car, getString(R.string.car));
        myItens[1] = new DonateItem(R.drawable.camera, getString(R.string.camera));
        myItens[2] = new DonateItem(R.drawable.computer, getString(R.string.computer));
        myItens[3] = new DonateItem(R.drawable.refrigerator, getString(R.string.refrigerator));
        myItens[4] = new DonateItem(R.drawable.microwaves, getString(R.string.microwaves));

    }
}
