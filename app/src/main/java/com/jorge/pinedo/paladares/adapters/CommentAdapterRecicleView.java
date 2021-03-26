package com.jorge.pinedo.paladares.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.models.CardComment;

import java.util.ArrayList;


public class CommentAdapterRecicleView extends RecyclerView.Adapter<CommentAdapterRecicleView.ChefViewHolder>{
    private static final String TAG = "ChefMenuAdapterRecicleView";
    private ArrayList<CardComment> cards;
    private int resource;
    private Activity activity;

    public CommentAdapterRecicleView(ArrayList<CardComment> cards, int resource, Activity activity) {
        this.cards = cards;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public ChefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ChefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChefViewHolder holder, int position) {
        final CardComment menu = cards.get(position);

        holder.card_name.setText(menu.getCard_name());
        holder.card_initial.setText(menu.getCard_name().substring(0,1));
        holder.card_title.setText(menu.getCard_title());
        holder.card_description.setText(menu.getCard_description());


       /* holder.card_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(activity,DetailMenuActivity.class);
                Bundle b=new Bundle();
                b.putSerializable("menu",menu);
                i.putExtras(b);
                activity.startActivity(i);
            }
        });*/



        /*holder.schedule_card.setText(chef.getSchedule_card());
        holder.number_commnet_card.setText(chef.getNumber_commnet_card());
        holder.number_point_card.setText(chef.getNumber_point_card());
        holder.number_point_card.setText(chef.getNumber_point_card());*/
//        holder.like_number.setText(chef.getLike_number());

//        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);
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
        return cards.size();
    }


    public class ChefViewHolder extends RecyclerView.ViewHolder{

        private ImageView card_photo;
        private TextView card_name;
        private TextView card_title;
        private TextView card_description;
        private TextView card_initial;
        private CardView card_complete;

        public ChefViewHolder(View itemView) {
            super(itemView);

            card_photo = itemView.findViewById(R.id.card_photo);
            card_name = itemView.findViewById(R.id.card_name);
            card_title = itemView.findViewById(R.id.card_title);
            card_description= itemView.findViewById(R.id.card_description);
            card_initial= itemView.findViewById(R.id.card_initial);

           /* pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard);
            name_chef_card = (TextView) itemView.findViewById(R.id.name_chef_card);
            name_business_card = (TextView) itemView.findViewById(R.id.name_business_card);
            schedule_card = (TextView) itemView.findViewById(R.id.schedule_card);
            number_commnet_card = (TextView) itemView.findViewById(R.id.number_commnet_card);
            number_point_card = (TextView) itemView.findViewById(R.id.number_point_card);*/
//            likenumberCard= (TextView) itemView.findViewById(R.id.likenumber_card);


        }
    }
}

