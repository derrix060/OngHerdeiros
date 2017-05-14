package com.example.mario.ongproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mario on 13/05/2017.
 */

public class DetachItemAdapter extends RecyclerView.Adapter<DetachItemAdapter.ItemViewHolder> {

    private DonateItem[] myItens;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView title;
        //ImageView img;

        public ItemViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.detach_card);
            //img = (ImageView)itemView.findViewById(R.id.detach_img_item);
            title = (TextView)itemView.findViewById(R.id.title_card_text);
        }
    }

    // Constructor
    public DetachItemAdapter(DonateItem[] myItens){
        this.myItens = myItens;
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
        //itemViewHolder.img.setImageResource(myItens.get(position).imageId);
        itemViewHolder.title.setText(myItens[position].title);
    }

    @Override
    public int getItemCount() {
        return myItens.length;
    }
}
