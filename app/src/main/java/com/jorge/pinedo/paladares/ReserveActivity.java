package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;

public class ReserveActivity extends AppCompatActivity {

    Button btn_reserve;

    private static final int REQUEST_CODE = 100;
    private static final int RESULT_CANCELED = 101;

    MercadoPagoCheckout checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);


        btn_reserve=findViewById(R.id.btn_reserve);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout=new MercadoPagoCheckout
                    .Builder("TEST-2ef9627a-353c-48e4-8782-cde57e6f8875","397912362-72ede695-8bd1-43f1-8ddb-b6b78e07c055")
                    .build();
                checkout.startPayment(ReserveActivity.this,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == REQUEST_CODE){

            if(resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE){
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                Log.d("MercadoPago",payment.getPaymentStatus());
                Intent i=new Intent(ReserveActivity.this,PayProcessActivity.class);
                startActivity(i);
            }else if(resultCode == RESULT_CANCELED){
                if (data != null && data.getExtras() != null && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError = (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);

                    Log.d("MercadoPago Canceled",mercadoPagoError.getMessage());
                    Toast.makeText(ReserveActivity.this,"Pago Canceled"+mercadoPagoError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
