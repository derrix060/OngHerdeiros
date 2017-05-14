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

    private List<DonateItem> myItens;

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

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.detach_rv);

        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // Initialize itens
        initializeData();

        DetachItemAdapter adapter = new DetachItemAdapter(myItens);
        mRecyclerView.setAdapter(adapter);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        return v;
    }


    private void initializeData(){

        myItens.add(new DonateItem(1, "Title1", "Description1"));
        myItens.add(new DonateItem(2, "Title2", "Description2"));
        myItens.add(new DonateItem(3, "Title3", "Description3"));
    }
}
