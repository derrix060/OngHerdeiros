package com.example.mario.ongproject.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mario.ongproject.model.Mail;
import com.example.mario.ongproject.R;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

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

        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail();
        email.m.setBody(createMessage());
        email.execute();
    }

    protected void displayMessage(String message){
        // progressBar.setVisibility(View.GONE);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    ContactFragment activity;

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                activity.displayMessage("Email sent.");
            } else {
                activity.displayMessage("Email failed to send.");
            }

            return true;
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            activity.displayMessage("Authentication failed.");
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            activity.displayMessage("Email failed to send.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            activity.displayMessage("Unexpected error occured.");
            return false;
        }
    }
}
