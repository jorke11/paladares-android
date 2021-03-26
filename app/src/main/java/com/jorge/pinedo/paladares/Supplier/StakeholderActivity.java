package com.jorge.pinedo.paladares.Supplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.SupplierActivity;
import com.jorge.pinedo.paladares.SupplierManager;
import com.jorge.pinedo.paladares.entites.GenericResponse;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StakeholderActivity extends AppCompatActivity {

    @BindView(R.id.business)
    EditText ed_business;

    @BindView(R.id.document)
    EditText ed_document;

    @BindView(R.id.phone)
    EditText ed_phone;

    @BindView(R.id.url_image)
    ImageView url_image;

    ApiService service;
    Call<GenericResponse> call;
    SupplierManager supplierManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stakeholder);
        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);

        supplierManager = SupplierManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        ed_business.setText(supplierManager.getBusiness());

        service = RetrofitBuilder.createService(ApiService.class);
    }

    @OnClick(R.id.btn_save)
    void register(){

        String business=ed_business.getText().toString();
        String document=ed_document.getText().toString();
        String phone=ed_phone.getText().toString();
        String url_image="";

        call = service.createStakeholder(business,document,phone,"");

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    /*GenericResponse resp=response.body();
                    Toast.makeText(getApplicationContext(),resp.getMsg().toString(),Toast.LENGTH_LONG).show();*/

                    supplierManager.setIs_supplier("true");
                    Intent i=new Intent(StakeholderActivity.this,SupplierActivity.class);
                    startActivity(i);

                } else {
                    //handleErrors(response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.d("JORGE", "onFailure" + t.getMessage());
            }
        });


    }

}
