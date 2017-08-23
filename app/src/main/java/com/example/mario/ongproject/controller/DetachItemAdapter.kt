package com.example.mario.ongproject.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mario.ongproject.R
import com.example.mario.ongproject.model.DonateItem
import java.util.*

/**
 * Created by mario on 13/05/2017.
 */

class DetachItemAdapter// Constructor
(private val myItems: ArrayList<DonateItem>) : RecyclerView.Adapter<DetachItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView
        internal var title: TextView
        internal var img: ImageView
        internal var btnShare: Button
        internal var btnDonate: Button

        init {

            cv = itemView.findViewById(R.id.detach_card) as CardView
            img = itemView.findViewById(R.id.detach_img_item) as ImageView
            title = itemView.findViewById(R.id.title_card_text) as TextView
            btnShare = itemView.findViewById(R.id.detach_button_share) as Button
            btnDonate = itemView.findViewById(R.id.detach_button_donate) as Button

            // Set description when talkback is activated
            img.contentDescription = title.text.toString() + R.string.image

            btnShare.setOnClickListener { v ->
                val shareBody = String.format(v.resources.getString(R.string.share_message), title.text.toString())
                val sub = v.resources.getString(R.string.share_subtitle)

                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub)
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)

                startActivity(v.context, sharingIntent, Bundle())
            }

            btnDonate.setOnClickListener { v ->
                val donateIntent = Intent(Intent.ACTION_VIEW)
                donateIntent.data = Uri.parse("http://www.herdeirosdofuturo.org.br/fa%C3%A7a-sua-doa%C3%A7%C3%A3o.html")
                startActivity(v.context, donateIntent, Bundle())
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): DetachItemAdapter.ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.detach_list_item, viewGroup, false)

        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(ivh: ItemViewHolder, position: Int) {
        val item = myItems[position]
        ivh.title.text = item.title
        if (!item.imagePath.isEmpty()) {
            LoadImageTask(ivh.img).execute(item.imagePath)
        }
    }

    override fun getItemCount(): Int {
        return myItems.size
    }
}
