package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.pinedo.paladares.NewClient.NewClientActivity;
import com.jorge.pinedo.paladares.NewSupplier.NewSupplierActivity;
import com.jorge.pinedo.paladares.adapters.FavoriteAdapterRecicleView;
import com.jorge.pinedo.paladares.entites.Product;
import com.jorge.pinedo.paladares.entites.ProductResponse;
import com.jorge.pinedo.paladares.entites.UserResponse;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;
import com.squareup.haha.perflib.Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    NavigationView navigationView;
    TextView title_menu,change_mode;
    View headerView;

    UserManager userManager;

    TokenManager tokenManager;
    ApiService apiService;
    Call<ProductResponse> call;

    ImageView test;

    RecyclerView favoriteRecycler,newRecycler;
    LinearLayoutManager linearLayoutManager,newlinearLayoutManager;
    private FavoriteAdapterRecicleView favoriteAdapterRecicleView,newAdapterRecicleView;

    SupplierManager supplierManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        showToolbar("",true);

        userManager = UserManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        tokenManager=TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        apiService = RetrofitBuilder.createServiceWithAuth(ApiService.class);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_barradenavegacion);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navegador_black);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        headerView = navigationView.getHeaderView(0);
        change_mode = headerView.findViewById(R.id.change_mode);
        supplierManager = SupplierManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));


        change_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplier = userManager.getSupplier().toString();

                startActivity(new Intent(MainActivity.this,NewSupplierActivity.class));
                /*
                if(!supplierManager.getIs_supplier().equals("true")){
                    startActivity(new Intent(MainActivity.this,NewSupplierActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,SupplierActivity.class));
                }*/
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

        favoriteRecycler=findViewById(R.id.reciclerLike);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        favoriteRecycler.setLayoutManager(linearLayoutManager);

        newRecycler=findViewById(R.id.reciclerNew);
        newlinearLayoutManager=new LinearLayoutManager(this);
        newlinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        newRecycler.setLayoutManager(newlinearLayoutManager);

        getFavorites();
        getNewPaladares();
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            String token = tokenManager.getToken().getAccess_token();
            if(token.isEmpty()){
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }

        }catch (NullPointerException e){
            Intent i=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }

    public void getFavorites(){
        call = apiService.getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d(TAG,"onResponse " +response);

                if(response.isSuccessful()){

                    favoriteAdapterRecicleView = new FavoriteAdapterRecicleView(response.body().getData(),R.layout.cardview_favorites,MainActivity.this);
                    favoriteRecycler.setAdapter(favoriteAdapterRecicleView);

                    /*for(Product product: response.body().getData()){
                        Log.d("JORGE",product.getTitle());
                    }*/
                    //title.setText(response.body().getData().get(0).getTitle());
                }else {
                    if (response.code() == 401) {
                        tokenManager.deleteToken();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG,"onFailure" +t.getMessage());
            }
        });
    }

    public void getNewPaladares(){
        call=apiService.getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d(TAG,"onResponse " +response);

                if(response.isSuccessful()){

                    newAdapterRecicleView = new FavoriteAdapterRecicleView(response.body().getData(),R.layout.cardview_favorites,MainActivity.this);
                    newRecycler.setAdapter(newAdapterRecicleView);

                }else {
                    if (response.code() == 401) {
                        tokenManager.deleteToken();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG,"onFailure" +t.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(call!=null){
            call.cancel();
            call=null;
        }
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void goToMenu(View view){
        Intent i=new Intent(MainActivity.this, ListChefActivity.class);
        startActivity(i);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragmentSelected = false;

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_close) {
            tokenManager.deleteToken();
            Intent i=new Intent(MainActivity.this,NewClientActivity.class);
            startActivity(i);
        }

        if (fragmentSelected == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
