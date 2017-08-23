package com.example.mario.ongproject.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mario.ongproject.R;
import com.example.mario.ongproject.controller.SendEmailAsyncTask;
import com.example.mario.ongproject.model.Mail;

/**
 * Created by mario on 05/05/17.
 */

public class ContactFragment  extends Fragment {
    FloatingActionButton sendFAB;
    TextView email_to;
    TextView name;
    TextView phone_number;
    TextView message;
    ProgressBar progressBar;

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

        email_to = (TextView) v.findViewById(R.id.editTxtMail);
        name = (TextView) v.findViewById(R.id.editTxtName);
        phone_number = (TextView) v.findViewById(R.id.editTxtPhone);
        message = (TextView) v.findViewById(R.id.editTxtMessage);
        // progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_contact);
        // progressBar.setVisibility(View.INVISIBLE);

        // Inflate the layout for this fragment
        return v;
    }

    private String createMessage(){
        String msg;
        // progressBar.setVisibility(View.VISIBLE);

        msg = "Esta mensagem foi criada autometicamente pelo aplicativo Android.\n";
        msg += "\nNome: " +  name.getText().toString();
        msg += "\nEmail: " + email_to.getText().toString();
        msg += "\nTelefone: " + phone_number.getText().toString();
        msg += "\n\n\nMensagem:\n" + message.getText().toString();

        return msg;
    }

    private void sendMessage() {

        Mail m = new Mail();
        m.setBody(createMessage());
        Snackbar.make(getView(), getString(R.string.sending), Snackbar.LENGTH_LONG)
                .show();
        SendEmailAsyncTask email = new SendEmailAsyncTask(this, m);
        email.execute();
    }

    public void displayMessage(String message){
        // progressBar.setVisibility(View.GONE);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

}


