package com.jorge.pinedo.paladares.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.pinedo.paladares.ProfileActivity;
import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.entites.Chef;

import java.util.ArrayList;
import java.util.List;


public class ChefAdapterRecicleView extends RecyclerView.Adapter<ChefAdapterRecicleView.ChefViewHolder>{
    private static final String TAG = "ChefAdapterRecicleView";
    private List<Chef> listItem,filterList;
    private int resource;
    private Activity activity;

    public ChefAdapterRecicleView(List<Chef> listItem, int resource, Activity activity) {

        this.listItem = listItem;
        this.resource = resource;
        this.activity = activity;
        this.filterList=new ArrayList<Chef>();
        this.filterList.addAll(this.listItem);
    }

    @Override
    public ChefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ChefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChefViewHolder holder, int position) {
        final Chef chef = filterList.get(position);



        holder.business.setText(chef.getBusiness());
        //holder.card_star.setText(chef.getCard_star());

        //holder.card_menu.setText(chef.getCard_menu());
        //holder.card_price.setText(chef.getCard_price());


      holder.card_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ProfileActivity.class);
                Bundle b=new Bundle();
                b.putSerializable("chef",chef);
                i.putExtras(b);
                activity.startActivity(i);
            }
        });

        /*holder.schedule_card.setText(chef.getSchedule_card());
        holder.number_commnet_card.setText(chef.getNumber_commnet_card());
        holder.number_point_card.setText(chef.getNumber_point_card());
        holder.number_point_card.setText(chef.getNumber_point_card());*/
//        holder.like_number.setText(chef.getLike_number());

        //Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);
//        Log.d(TAG,chef.getCard_photo());/**/
        //Picasso.get().load(chef.getCard_photo()).into(holder.card_photo);

//
       /*/ holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, DetailChefActivity.class);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                } else {
                    activity.startActivity(intent);
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }


    public class ChefViewHolder extends RecyclerView.ViewHolder{

        private ImageView card_photo;
        private TextView business;
        private TextView card_star;
        private TextView card_menu;
        private TextView card_price;
        private CardView card_complete;

        public ChefViewHolder(View itemView) {
            super(itemView);

            business= itemView.findViewById(R.id.business);

//            card_star = itemView.findViewById(R.id.card_star);
            //card_menu = itemView.findViewById(R.id.card_menu);
            //card_price = itemView.findViewById(R.id.card_price)
            card_complete = itemView.findViewById(R.id.card_complete);
            card_photo = itemView.findViewById(R.id.card_photo);
        }
    }
}

