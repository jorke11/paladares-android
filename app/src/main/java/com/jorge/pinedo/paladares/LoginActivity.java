package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.jorge.pinedo.paladares.entites.AccessToken;
import com.jorge.pinedo.paladares.entites.ChefResponse;
import com.jorge.pinedo.paladares.network.ApiError;
import com.jorge.pinedo.paladares.network.ApiService;
import com.jorge.pinedo.paladares.network.RetrofitBuilder;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.username)
    EditText ed_username;

    @BindView(R.id.password)
    EditText ed_password;

    ApiService service;
    Call<AccessToken> call;

    TokenManager tokenManager;
    AwesomeValidation validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        tokenManager=TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
    }



    @OnClick(R.id.btn_login)
    void register(){

        String username = ed_username.getText().toString();
        String password = ed_password.getText().toString();

        call = service.login(username, password);

        call.enqueue(new Callback<AccessToken>() {
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    Log.d("JORGE",response.body().toString());
                    tokenManager.saveToken(response.body());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                } else {
                    handleErrors(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    private void handleErrors(ResponseBody response) {
        ApiError apiError=Utils.convertErrors(response);

        Log.d("JORKE11","llego");

        //Log.d("JORKE11",response.toString());

        for(Map.Entry<String,List<String>> error:apiError.getErrors().entrySet()){

            if(error.getKey().equals("username")){
                ed_username.setError(error.getValue().get(0));
            }

            if(error.getKey().equals("password")){
                ed_password.setError(error.getValue().get(0));
            }
        }
    }

    public void setupRules(){
/*        validator.addValidation(this,R.id.til_name,RegexTemplate.NOT_EMPTY,R.string.err_name);
        validator.addValidation(this,R.id.til_email, Patterns.EMAIL_ADDRESS,R.string.err_email);
        validator.addValidation(this,R.id.til_password, "[a-zA-Z0-9]{6,}",R.string.err_password);*/
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("JORGETOKEN",tokenManager.getToken().toString());
    }

    public void register(View view){
        Intent i=new Intent(this,RegisterActivity.class);
        startActivity(i);
    }


}

