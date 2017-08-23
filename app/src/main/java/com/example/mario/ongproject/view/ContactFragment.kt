package com.example.mario.ongproject.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mario.ongproject.R
import com.example.mario.ongproject.controller.SendEmailAsyncTask
import com.example.mario.ongproject.model.Mail

/**
 * Created by mario on 05/05/17.
 */

class ContactFragment : Fragment() {
    private lateinit var sendFAB: FloatingActionButton
    private lateinit var email_to: TextView
    private lateinit var name: TextView
    private lateinit var phone_number: TextView
    private lateinit var message: TextView
    // internal var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.contact_view, container, false)
        sendFAB = v.findViewById(R.id.floatingActionButton) as FloatingActionButton

        sendFAB.setOnClickListener { sendMessage() }

        email_to = v.findViewById(R.id.editTxtMail) as TextView
        name = v.findViewById(R.id.editTxtName) as TextView
        phone_number = v.findViewById(R.id.editTxtPhone) as TextView
        message = v.findViewById(R.id.editTxtMessage) as TextView
        // progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_contact);
        // progressBar.setVisibility(View.INVISIBLE);

        // Inflate the layout for this fragment
        return v
    }

    private fun createMessage(): String {
        var msg: String
        // progressBar.setVisibility(View.VISIBLE);

        msg = "Esta mensagem foi criada autometicamente pelo aplicativo Android.\n"
        msg += "\nNome: " + name.text.toString()
        msg += "\nEmail: " + email_to.text.toString()
        msg += "\nTelefone: " + phone_number.text.toString()
        msg += "\n\n\nMensagem:\n" + message.text.toString()

        return msg
    }

    private fun sendMessage() {

        val m = Mail()
        m.setBody(createMessage())
        Snackbar.make(view!!, getString(R.string.sending), Snackbar.LENGTH_LONG)
                .show()
        val email = SendEmailAsyncTask(this, m)
        email.execute()
    }

    fun displayMessage(message: String) {
        // progressBar.setVisibility(View.GONE);
        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
    }

}


