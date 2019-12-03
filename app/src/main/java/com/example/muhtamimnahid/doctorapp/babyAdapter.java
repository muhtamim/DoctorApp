package com.example.muhtamimnahid.doctorapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class babyAdapter extends RecyclerView.Adapter<babyAdapter.ViewHolder> {

    private List<baby_model> itemList = new ArrayList<baby_model>();
    private Context context;
    private TextView tv;
    private int lastPosition = -1;


    public babyAdapter(List<baby_model> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public babyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_baby, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        setAnimation(holder.container, position);

        final baby_model event = itemList.get(position);

        holder.tvTitle.setText(event.getName());
        holder.tvDetails.setText(event.getDescription());


        Log.d("Tag", "Item name: " + event.getName() + ", Link " + event.getImageUrl());
        Picasso.with(context).load(event.getImageUrl()).fit().into(holder.ivBanner);

        holder.ibExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tvDetails.getVisibility() == v.GONE) {
                    holder.tvDetails.setVisibility(v.VISIBLE);

                } else if (holder.tvDetails.getVisibility() == v.VISIBLE) {
                    holder.tvDetails.setVisibility(v.GONE);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CardView container;
        ImageView ivBanner;
        TextView tvTitle;
        TextView tvDetails;

        View ibExpand;


        public ViewHolder(View itemView) {
            super(itemView);

            container = (CardView) itemView.findViewById(R.id.item_event_container);
            ivBanner = (ImageView) itemView.findViewById(R.id.iv_item_upcoming_cover);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_upcoming_title);
            tvDetails = (TextView) itemView.findViewById(R.id.tv_item_event_details);

            ibExpand = itemView.findViewById(R.id.layout_item_upcoming_explore);


        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
