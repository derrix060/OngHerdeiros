package com.example.mario.ongproject.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.ongproject.R;
import com.example.mario.ongproject.model.Event;
import com.example.mario.ongproject.view.EventDetailActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

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
        CardView cardView;
        Button shareBtn;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.event_img_item);
            title = (TextView)itemView.findViewById(R.id.txt_event_title);
            date = (TextView) itemView.findViewById(R.id.txt_event_date);
            time = (TextView) itemView.findViewById(R.id.txt_event_time);
            cardView = (CardView) itemView.findViewById(R.id.event_item_card);
            shareBtn = (Button) itemView.findViewById(R.id.event_button_share);

        }
    }

    // Constructor
    public EventItemAdapter(ArrayList<Event> myItems){
        this.myItems = myItems;
    }

    @Override
    public EventItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_list_item, viewGroup, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder ivh, int position) {
        final Event item = myItems.get(position);
        ivh.title.setText(item.getTitle());
        ivh.date.setText(item.getDate());
        ivh.time.setText(item.getTime());

        if (!item.getImg_src().isEmpty()){
            new LoadImageTask(ivh.img).execute(item.getImg_src());
        }

        ivh.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEventDetails(v.getContext(), item);
            }
        });


        ivh.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = String.format(v.getResources().getString(R.string.events_share),
                        item.getDate(), item.getTime(), item.getTitle(), item.getDescription());

                String sub = v.getResources().getString(R.string.share_subtitle);

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                startActivity(v.getContext(), sharingIntent, new Bundle());
            }
        });

    }

    public void openEventDetails(Context context, Event event){
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("EVENT_TITLE", event.getTitle());
        intent.putExtra("EVENT_DATE", event.getDate());
        intent.putExtra("EVENT_TIME", event.getTime());
        intent.putExtra("EVENT_PLACE", event.getPlace());
        intent.putExtra("EVENT_DESCRIPTION", event.getDescription());

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
            return myItems.size();
        }


    private class LoadImageTask extends AsyncTask<String,Void,Bitmap> {
        private ImageView imageView;

        public LoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;

            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                try (InputStream inputStream = connection.getInputStream()) {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }

            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
