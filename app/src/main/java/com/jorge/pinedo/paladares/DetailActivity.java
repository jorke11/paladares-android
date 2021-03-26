package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static final int RESULT_CANCELED = 101;

    MercadoPagoCheckout checkout;

    CircleImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

    public void onClick(View view){
        Intent i = new Intent(DetailActivity.this,ProfileActivity.class);
        startActivity(i);
    }


    public void showProfile(View view){
        Intent i=new Intent(this,ProfileActivity.class);
        startActivity(i);
    }

    public void reserve(View view){
        Intent i=new Intent(this,ReserveActivity.class);
        startActivity(i);

        /*checkout=new MercadoPagoCheckout
                .Builder("TEST-2ef9627a-353c-48e4-8782-cde57e6f8875","397912362-72ede695-8bd1-43f1-8ddb-b6b78e07c055")
                .build();
        checkout.startPayment(DetailActivity.this,REQUEST_CODE);*/
    }

    public void showToolbar(String titulo,boolean upButton,View view){
        Toolbar toolbar =(Toolbar)view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
