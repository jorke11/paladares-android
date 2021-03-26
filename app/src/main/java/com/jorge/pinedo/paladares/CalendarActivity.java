package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;

import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar_view;
    private static final int REQUEST_CODE = 100;
    private static final int RESULT_CANCELED = 101;

    MercadoPagoCheckout checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        showToolbar("",true);

        calendar_view=findViewById(R.id.calendar_view);

        calendar_view.setMinDate(new Date().getTime());

        calendar_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                checkout=new MercadoPagoCheckout
                        .Builder("TEST-2ef9627a-353c-48e4-8782-cde57e6f8875","397912362-72ede695-8bd1-43f1-8ddb-b6b78e07c055")
                        .build();
                checkout.startPayment(CalendarActivity.this,REQUEST_CODE);

                /*Intent i=new Intent(CalendarActivity.this,ReserveActivity.class);
                startActivity(i);*/
            }
        });
    }


    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(0x000000);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        //CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == REQUEST_CODE){

            if(resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE){
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                Log.d("MercadoPago",payment.getPaymentStatus());
                Intent i=new Intent(CalendarActivity.this,ReserveActivity.class);
                startActivity(i);
            }else if(resultCode == RESULT_CANCELED){
                if (data != null && data.getExtras() != null && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError = (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);

                    Log.d("MercadoPago Canceled",mercadoPagoError.getMessage());
                    Toast.makeText(CalendarActivity.this,"Pago Canceled"+mercadoPagoError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
