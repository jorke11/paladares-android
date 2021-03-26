package com.jorge.pinedo.paladares.Supplier.ManagementProducts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.entites.GenericResponse;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText ed_title;

    @BindView(R.id.description)
    EditText ed_description;

    @BindView(R.id.price)
    EditText ed_price;

    @BindView(R.id.url_image)
    ImageView url_image;

    ApiService service;
    Call<GenericResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
    }



    @OnClick(R.id.btn_save)
    void register(){
        String title=ed_title.getText().toString();
        String description=ed_description.getText().toString();
        String price=ed_price.getText().toString();
        String url_image="";

        call = service.createProducts(title,description,price,"");

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    onBackPressed();
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
