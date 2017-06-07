package com.example.mario.ongproject.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.ongproject.model.DonateItem;
import com.example.mario.ongproject.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by mario on 13/05/2017.
 */

public class DetachItemAdapter extends RecyclerView.Adapter<DetachItemAdapter.ItemViewHolder> {

    private ArrayList<DonateItem> myItems;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView title;
        ImageView img;
        Button btnShare;
        Button btnDonate;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.detach_card);
            img = (ImageView)itemView.findViewById(R.id.detach_img_item);
            title = (TextView)itemView.findViewById(R.id.title_card_text);
            btnShare = (Button) itemView.findViewById(R.id.detach_button_share);
            btnDonate = (Button) itemView.findViewById(R.id.detach_button_donate);

            // Set description when talkback is activated
            img.setContentDescription(title.getText().toString() + R.string.image);

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String shareBody = String.format(v.getResources().getString(R.string.share_message), title.getText().toString());
                    String sub = v.getResources().getString(R.string.share_subtitle);

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                    startActivity(v.getContext(), sharingIntent, new Bundle());
                }
            });

            btnDonate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent donateIntent = new Intent(Intent.ACTION_VIEW);
                    donateIntent.setData(Uri.parse("http://www.herdeirosdofuturo.org.br/fa%C3%A7a-sua-doa%C3%A7%C3%A3o.html"));
                    startActivity(v.getContext(), donateIntent, new Bundle());
                }
            });
        }
    }

    // Constructor
    public DetachItemAdapter(ArrayList<DonateItem> myItems){
        this.myItems = myItems;
    }

    @Override
    public DetachItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detach_list_item, viewGroup, false);

        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        DonateItem item = myItems.get(position);
        itemViewHolder.title.setText(item.title);
        itemViewHolder.img.setImageResource(item.imagePath);
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }
}
