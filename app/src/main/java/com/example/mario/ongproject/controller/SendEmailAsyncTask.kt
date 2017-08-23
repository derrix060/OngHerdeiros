package com.example.mario.ongproject.controller

import android.os.AsyncTask

import com.example.mario.ongproject.model.Mail
import com.example.mario.ongproject.view.ContactFragment

import javax.mail.AuthenticationFailedException
import javax.mail.MessagingException

/**
 * Created by mario on 23/08/17.
 */

class SendEmailAsyncTask(private val activity: ContactFragment, private val m: Mail) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void): Boolean? {
        try {
            if (m.send()) {
                activity.displayMessage("Email sent.")
            } else {
                activity.displayMessage("Email failed to send.")
            }

            return true
        } catch (e: AuthenticationFailedException) {
            e.printStackTrace()
            activity.displayMessage("Authentication failed.")
            return false
        } catch (e: MessagingException) {
            e.printStackTrace()
            activity.displayMessage("Email failed to send.")
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            activity.displayMessage("Unexpected error occured.")
            return false
        }

    }
}
