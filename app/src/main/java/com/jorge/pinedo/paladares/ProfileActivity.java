package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jorge.pinedo.paladares.adapters.CommentAdapterRecicleView;
import com.jorge.pinedo.paladares.models.CardComment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView picturesRecycler;
    CommentAdapterRecicleView chefAdapterRecicleView;

    TextView name_user;

    Button btn_reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        picturesRecycler=findViewById(R.id.commentRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);
        //buildPictures();

        btn_reserve=findViewById(R.id.btn_reserve);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,ListMenuActivity.class));
            }
        });

    }

    public void buildPictures(){
        ArrayList<CardComment> pictures = new ArrayList<>();
        pictures.add(new CardComment("Daniel polanco","Experiencia Pesima","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "));
        pictures.add(new CardComment("Natalia Molina","Buenisimo","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and m"));
        pictures.add(new CardComment("Juana","Ricoo","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and m"));
        pictures.add(new CardComment("Jorge","Ricoo","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and m"));
        pictures.add(new CardComment("Camila","Ricoo","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and m"));

        chefAdapterRecicleView = new CommentAdapterRecicleView(pictures,R.layout.card_item_comment,this);
        picturesRecycler.setAdapter(chefAdapterRecicleView);
    }
}
