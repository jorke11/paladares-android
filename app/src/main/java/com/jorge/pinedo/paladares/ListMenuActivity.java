package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jorge.pinedo.paladares.adapters.ListMenuAdapterRecicleView;
import com.jorge.pinedo.paladares.models.CardListMenu;

import java.util.ArrayList;

public class ListMenuActivity extends AppCompatActivity {

    RecyclerView listMenuRecycler;

    ArrayList<CardListMenu> pictures = new ArrayList<>();
    private ListMenuAdapterRecicleView listMenuAdapterRecicleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        listMenuRecycler=findViewById(R.id.listMenuRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listMenuRecycler.setLayoutManager(linearLayoutManager);

        buildPictures();
    }

    public void onClick(View view){
        Intent i = new Intent(ListMenuActivity.this,ProfileActivity.class);
        startActivity(i);
    }


    public void reserve(View view){
        Intent i = new Intent(ListMenuActivity.this,DetailActivity.class);
        startActivity(i);
    }



    public void buildPictures(){



        pictures.add(new CardListMenu("Tomate, Soup",
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                "13 Vie"));
        pictures.add(new CardListMenu("Other, Menu1",
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                "14 Vie"));
        pictures.add(new CardListMenu("Other, Menu2",
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                "15 Vie"));
        pictures.add(new CardListMenu("Other, Menu3",
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                "18 Vie"));

        listMenuAdapterRecicleView=new ListMenuAdapterRecicleView(pictures,R.layout.card_view_menu,this);
        listMenuRecycler.setAdapter(listMenuAdapterRecicleView);



    }

}
