package com.example.mario.ongproject.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.ongproject.R;
import com.example.mario.ongproject.model.Event;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        ivh.date.setText(item.getDate());
        ivh.time.setText(item.getTime());

        if (!item.getImg_src().isEmpty()){
            new LoadImageTask(ivh.img).execute(item.getImg_src());
        }

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