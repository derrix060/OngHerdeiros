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

        myItens[0] = new DonateItem(1, "Carro");
        myItens[1] = new DonateItem(2, "Geladeira");
        myItens[2] = new DonateItem(3, "Fogão");
        myItens[3] = new DonateItem(2, "Microondas");
        myItens[4] = new DonateItem(3, "Computador");
    }
}
