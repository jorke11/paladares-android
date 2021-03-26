package com.jorge.pinedo.paladares.Supplier.ManagementProducts;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jorge.pinedo.paladares.LoginActivity;
import com.jorge.pinedo.paladares.MainActivity;
import com.jorge.pinedo.paladares.R;
import com.jorge.pinedo.paladares.adapters.FavoriteAdapterRecicleView;
import com.jorge.pinedo.paladares.entites.AccessToken;
import com.jorge.pinedo.paladares.entites.GenericResponse;
import com.jorge.pinedo.paladares.entites.Product;
import com.jorge.pinedo.paladares.entites.ProductResponse;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {


    ApiService service;
    Call<ProductResponse> call;

    RecyclerView recyclerProduct;
    LinearLayoutManager linearLayoutManager;

    private FavoriteAdapterRecicleView favoriteAdapterRecicleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);

        recyclerProduct=findViewById(R.id.recyclerProduct);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerProduct.setLayoutManager(linearLayoutManager);

        getProducts();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    public void getProducts(){
        call = service.getProducts();


        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d("JORKE11","onResponse " +response);

                if(response.isSuccessful()){

                    favoriteAdapterRecicleView = new FavoriteAdapterRecicleView(response.body().getData(),R.layout.cardview_products,ProductActivity.this);
                    recyclerProduct.setAdapter(favoriteAdapterRecicleView);

                    /*for(Product product: response.body().getData()){
                        Log.d("JORGE",product.getTitle());
                    }*/
                    //title.setText(response.body().getData().get(0).getTitle());
                }else {
                    if (response.code() == 401) {
                        //tokenManager.deleteToken();
                        startActivity(new Intent(ProductActivity.this, LoginActivity.class));
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("JORKE","onFailure" +t.getMessage());
            }
        });
    }


    @OnClick(R.id.btn_new)
    void register(){
        Intent i=new Intent(ProductActivity.this,NewProductActivity.class);
        startActivity(i);
    }

//


}
