package com.jorge.pinedo.paladares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginSocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_social);
    }

    public void onClick(View view){
        //Intent i=new Intent(this,ListChefActivity.class);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void register(View view){
        Intent i=new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
}
