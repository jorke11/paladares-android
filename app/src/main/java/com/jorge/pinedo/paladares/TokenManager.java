package com.jorge.pinedo.paladares;

import android.content.SharedPreferences;
import android.util.Log;

import com.jorge.pinedo.paladares.entites.AccessToken;

public class TokenManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences preferences){
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    static synchronized TokenManager getInstance(SharedPreferences preferences){
         if(INSTANCE == null){
            INSTANCE = new TokenManager(preferences);
         }

         return INSTANCE;
    }

    public void saveToken(AccessToken token){

        editor.putString("ACCESS_TOKEN",token.getAccess_token()).commit();
        editor.putString("REFRESH_TOKEN",token.getRefresh_token()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setAccess_token(preferences.getString("ACCESS_TOKEN",null));
        token.setRefresh_token(preferences.getString("REFRESH_TOKEN",null));
        return token;
    }

}
