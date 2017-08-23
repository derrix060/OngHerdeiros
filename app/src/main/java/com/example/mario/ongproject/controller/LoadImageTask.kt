package com.example.mario.ongproject.controller

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by mario on 23/08/17.
 */

class LoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String): Bitmap? {
        var bitmap: Bitmap? = null

        var connection: HttpURLConnection? = null

        try {
            val url = URL(params[0])
            connection = url.openConnection() as HttpURLConnection
            try {
                bitmap = BitmapFactory.decodeStream(connection.inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection!!.disconnect()
        }

        return bitmap
    }

    override fun onPostExecute(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }
}
