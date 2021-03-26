package com.jorge.pinedo.paladares;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jorge.pinedo.paladares.adapters.ChefAdapterRecicleView;
import com.jorge.pinedo.paladares.entites.ChefResponse;
import com.jorge.pinedo.paladares.entites.GenericResponse;
import com.jorge.pinedo.paladares.entites.Chef;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class ListChefActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener, Response.Listener<JSONObject>,Response.ErrorListener
{

    private static final String TAG = "ListChefActivity";
    private static final String IP = "http://192.168.0.50";
    private ChefAdapterRecicleView chefAdapterRecicleView;
    ToggleButton btnBurger,btnChicken,btnPasta,btnPizza;
    ImageView btnFilter;
    List<String> f=new ArrayList();
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    RecyclerView picturesRecycler;
    TextView text_list;

    StringRequest stringRequest;
    ArrayList<Chef> pictures = new ArrayList<>();

    List<String> catetories=new ArrayList<>();

    ApiService service;
    Call<ChefResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chef);

        listCategories();

        btnBurger = findViewById(R.id.btn_burger);
        btnChicken = findViewById(R.id.btn_chicken);
        btnPasta = findViewById(R.id.btn_pasta);
        btnPizza= findViewById(R.id.btn_pizza);
        //btnFilter= findViewById(R.id.btn_filter);
     /*   text_list= findViewById(R.id.text_list);

        text_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ListChefActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });*/

        requestQueue= Volley.newRequestQueue(ListChefActivity.this);
        profiles();

    /*    btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"rtes");
            }
        });*/


        picturesRecycler=findViewById(R.id.chefRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);
        //chefAdapterRecicleView=new ChefAdapterRecicleView(pictures,R.layout.card_view_chef,this);
        //picturesRecycler.setAdapter(chefAdapterRecicleView);

        SearchView txtsearch = findViewById(R.id.txtsearch);
        txtsearch.setIconifiedByDefault(false);
        txtsearch.setOnQueryTextListener(this);
        txtsearch.setSubmitButtonEnabled(true);
        txtsearch.setQueryHint("Que estas buscando");


        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);


        buildPictures();
    }

    public void buildPictures(){


        call = service.getStakeholders();

        call.enqueue(new Callback<ChefResponse>() {
            @Override
            public void onResponse(Call<ChefResponse> call, retrofit2.Response<ChefResponse> response) {
                if (response.isSuccessful()) {
                    chefAdapterRecicleView=new ChefAdapterRecicleView(response.body().getData(),R.layout.card_view_chef,ListChefActivity.this);
                    picturesRecycler.setAdapter(chefAdapterRecicleView);

                } else {
                    //handleErrors(response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<ChefResponse> call, Throwable t) {
                Log.d("JORGE", "onFailure" + t.getMessage());
            }
        });


       /* pictures.add(new Chef("http://www.novalandtours.com/images/guide/guilin.jpg","Uriel Ramírez", "4.6", "Pizza",
                "12.000","pizza"));

        pictures.add(new Chef(
                "http://www.novalandtours.com/images/guide/guilin.jpg",
                "Otro chef", "1.5", "Hamburguesa ejemplo",
                "60.000","burger"));

        pictures.add(new Chef(
                "http://www.novalandtours.com/images/guide/guilin.jpg",
                "Otro chef 2", "2.5", "sopa de algo",
                "30.000","pasta"));

        pictures.add(new Chef(
                "http://www.novalandtours.com/images/guide/guilin.jpg",
                "Otro chef 3", "5.0", "Pollo asaod",
                "50.000","chicken"));
        pictures.add(new Chef(
                "http://www.novalandtours.com/images/guide/guilin.jpg",
                "Otro chef 3", "4.5", "pizza lago",
                "10.000","ṕizza"));
*/






    }

    public void profiles(){

        String url="https://randomuser.me/api/?page=1&results=10";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }



    public  void listCategories(){
        catetories.add("pizza");
        catetories.add("burger");
        catetories.add("pasta");
        catetories.add("chicken");
    }

  /*  public void onClick(View view){
        String filter="";

        switch (view.getId()){
            case R.id.btn_burger:
                if(btnBurger.isChecked()){
                    f.add("burger");
                }else{
                    f.remove("burger");
                }
                break;

            case R.id.btn_chicken:
                if(btnChicken.isChecked()){
                    f.add("chicken");
                }else{
                    f.remove("chicken");
                }
                break;
            case R.id.btn_pasta:
                if(btnPasta.isChecked()){
                    f.add("pasta");
                }else{
                    f.remove("pasta");
                }
                break;
            case R.id.btn_pizza:
                if(btnPizza.isChecked()){
                    f.add("pizza");
                }else{
                    f.remove("pizza");
                }
                break;
        }

        chefAdapterRecicleView.filterCategoryList(f);

    }*/


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //chefAdapterRecicleView.filter(s);
        return true;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
