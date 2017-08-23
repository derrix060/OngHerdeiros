package com.example.mario.ongproject.controller

import android.content.Context
import android.content.Intent
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
import com.example.mario.ongproject.model.Event
import com.example.mario.ongproject.view.EventDetailActivity
import java.util.*

/**
 * Created by mario on 10/07/2017.
 */

class EventItemAdapter// Constructor
(private val myItems: ArrayList<Event>) : RecyclerView.Adapter<EventItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var img: ImageView
        internal var date: TextView
        internal var time: TextView
        internal var cardView: CardView
        internal var shareBtn: Button

        init {

            img = itemView.findViewById(R.id.event_img_item) as ImageView
            title = itemView.findViewById(R.id.txt_event_title) as TextView
            date = itemView.findViewById(R.id.txt_event_date) as TextView
            time = itemView.findViewById(R.id.txt_event_time) as TextView
            cardView = itemView.findViewById(R.id.event_item_card) as CardView
            shareBtn = itemView.findViewById(R.id.event_button_share) as Button

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): EventItemAdapter.ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.event_list_item, viewGroup, false)

        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(ivh: ItemViewHolder, position: Int) {
        val item = myItems[position]
        ivh.title.text = item.title
        ivh.date.text = item.date
        ivh.time.text = item.time

        if (!item.img_src.isEmpty()) {
            LoadImageTask(ivh.img).execute(item.img_src)
        }

        ivh.cardView.setOnClickListener { v -> openEventDetails(v.context, item) }


        ivh.shareBtn.setOnClickListener { v ->
            val shareBody = String.format(v.resources.getString(R.string.events_share),
                    item.date, item.time, item.title, item.description)

            val sub = v.resources.getString(R.string.share_subtitle)

            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub)
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)

            startActivity(v.context, sharingIntent, Bundle())
        }

    }

    fun openEventDetails(context: Context, event: Event) {
        val intent = Intent(context, EventDetailActivity::class.java)
        intent.putExtra("EVENT_TITLE", event.title)
        intent.putExtra("EVENT_DATE", event.date)
        intent.putExtra("EVENT_TIME", event.time)
        intent.putExtra("EVENT_PLACE", event.place)
        intent.putExtra("EVENT_DESCRIPTION", event.description)

        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return myItems.size
    }


}
