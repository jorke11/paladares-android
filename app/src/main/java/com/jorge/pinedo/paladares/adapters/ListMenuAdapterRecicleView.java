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

import com.jorge.pinedo.paladares.DetailActivity;
import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.models.CardListMenu;

import java.util.ArrayList;
import java.util.List;


public class ListMenuAdapterRecicleView extends RecyclerView.Adapter<ListMenuAdapterRecicleView.ChefViewHolder>{
    private static final String TAG = "ListMenuAdapter";
    private List<CardListMenu> listItem,filterList;
    private int resource;
    private Activity activity;

    public ListMenuAdapterRecicleView(ArrayList<CardListMenu> listItem, int resource, Activity activity) {

        this.listItem = listItem;
        this.resource = resource;
        this.activity = activity;
        this.filterList=new ArrayList<CardListMenu>();
        this.filterList.addAll(this.listItem);
    }

    @Override
    public ChefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ChefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChefViewHolder holder, int position) {
        final CardListMenu chef = filterList.get(position);

        //String name=(chef.getCard_name().length()>8)?chef.getCard_name().substring(0,6):chef.getCard_name();

        //holder.card_name.setText(name);
        //holder.card_menu.setText(chef.getCard_menu());
        //holder.card_price.setText(chef.getCard_price());


        holder.card_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetailActivity.class);
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

    public void filter(final String text){

        new Thread(new Runnable() {
            @Override
            public void run() {
                filterList.clear();
                if(TextUtils.isEmpty(text)){
                    filterList.addAll(listItem);
                }else{

                    for (CardListMenu item: listItem){
                        if(item.getCard_name().toLowerCase().contains(text.toLowerCase())){
                            Log.d(TAG,"ingreso "+item.getCard_name());
                            filterList.add(item);
                        }
                    }
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void filterCategory(final String category){

        new Thread(new Runnable() {
            @Override
            public void run() {
                filterList.clear();
                    for (CardListMenu item: listItem){
//                        if(item.getCard_category().toLowerCase().contains(category)){
//                            filterList.add(item);
//                        }
                    }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void filterCategoryList(final List<String> categories){

        new Thread(new Runnable() {
            @Override
            public void run() {
                filterList.clear();

                if(categories.size()==0) {
                    filterList.addAll(listItem);
                }else{
                    for (CardListMenu item: listItem){
                        for (String i : categories) {
//                            if (item.getCard_category().toLowerCase().contains(i)) {
//                                filterList.add(item);
//                            }
                        }

                    }
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }




    public class ChefViewHolder extends RecyclerView.ViewHolder{

        private ImageView card_photo;
        private TextView card_name;
        private TextView card_star;
        private TextView card_menu;
        private TextView card_price;
        private CardView card_complete;

        public ChefViewHolder(View itemView) {
            super(itemView);

            card_name = itemView.findViewById(R.id.card_name);
            card_star = itemView.findViewById(R.id.card_star);
            //card_menu = itemView.findViewById(R.id.card_menu);
            //card_price = itemView.findViewById(R.id.card_price);
            card_complete = itemView.findViewById(R.id.card_complete);
            card_photo = itemView.findViewById(R.id.card_photo);
        }
    }
}

