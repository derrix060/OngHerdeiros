package com.example.mario.ongproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by mario on 05/05/17.
 */

public class ContactFragment  extends Fragment {
    FloatingActionButton sendFAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.contact_view, container, false);
        sendFAB = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);

        sendFAB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                sendMessage();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void sendMessage(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            Toast.makeText(getActivity(), "Sending message.", Toast.LENGTH_SHORT).show();
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
