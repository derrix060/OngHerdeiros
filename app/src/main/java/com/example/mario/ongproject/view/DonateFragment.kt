package com.example.mario.ongproject.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

import com.example.mario.ongproject.R

/**
 * Created by mario on 05/05/17.
 */

class DonateFragment : Fragment() {

    private lateinit var imgDonate: ImageView
    private lateinit var seekDonate: SeekBar
    private lateinit var valueDonate: TextView
    private lateinit var donateCurrency: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.donate_new_view, container, false)


        val btnDonate = v.findViewById(R.id.btnDonate) as Button
        btnDonate.setOnClickListener { donate() }

        imgDonate = v.findViewById(R.id.imgChild) as ImageView
        seekDonate = v.findViewById(R.id.seekDonate) as SeekBar
        valueDonate = v.findViewById(R.id.txtDonateValue) as TextView
        donateCurrency = getText(R.string.currency).toString()

        seekDonate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeSeekBarValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })


        return v
    }

    private fun donate() {
        val uri = Uri.parse("https://mario-apra.tk/mercado_pago")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun changeSeekBarValue(newValue: Int?) {
        valueDonate.text = donateCurrency + newValue!!.toString()

        if (newValue < 20) {
            imgDonate.setImageResource(R.drawable.child_0)
        } else if (newValue < 40) {
            imgDonate.setImageResource(R.drawable.child_1)
        } else if (newValue < 60) {
            imgDonate.setImageResource(R.drawable.child_2)
        } else if (newValue < 80) {
            imgDonate.setImageResource(R.drawable.child_3)
        } else {
            imgDonate.setImageResource(R.drawable.child_4)
        }

    }
}
