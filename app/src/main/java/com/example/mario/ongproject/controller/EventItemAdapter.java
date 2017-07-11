package com.example.mario.ongproject.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.ongproject.R;
import com.example.mario.ongproject.model.Event;

import java.util.ArrayList;

/**
 * Created by mario on 10/07/2017.
 */

public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.ItemViewHolder> {

    private ArrayList<Event> myItems;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        TextView date;
        TextView time;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.event_img_item);
            title = (TextView)itemView.findViewById(R.id.txt_event_title);
            date = (TextView) itemView.findViewById(R.id.txt_event_date);
            time = (TextView) itemView.findViewById(R.id.txt_event_time);
        }
    }

    // Constructor
    public EventItemAdapter(ArrayList<Event> myItems){
        this.myItems = myItems;
    }

    @Override
    public EventItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detach_list_item, viewGroup, false);

        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder ivh, int position) {
        Event item = myItems.get(position);
        ivh.title.setText(item.getTitle());
        ivh.img.setImageBitmap(item.getImg_src());
        ivh.date.setText(item.getDate());
        ivh.time.setText(item.getTime());

    }

    @Override
    public int getItemCount() {
            return myItems.size();
        }



}
