package com.example.mario.ongproject.model

import android.graphics.Bitmap

/**
 * Created by mario on 10/07/2017.
 */

class Event(var title: String?, date: String, var place: String?, var description: String?, val img_src: String) {
    var date: String? = null
    var time: String? = null
    private var image: Bitmap? = null

    init {
        this.date = date.subSequence(0, 10).toString()
        this.time = date.subSequence(13, date.length).toString()
    }
}
