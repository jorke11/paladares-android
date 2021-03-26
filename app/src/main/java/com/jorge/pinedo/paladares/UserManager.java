package com.jorge.pinedo.paladares;

import android.content.SharedPreferences;

import com.jorge.pinedo.paladares.entites.User;

public class UserManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static UserManager INSTANCE = null;

    private UserManager(SharedPreferences preferences){
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    static synchronized UserManager getInstance(SharedPreferences preferences){
         if(INSTANCE == null){
            INSTANCE = new UserManager(preferences);
         }

         return INSTANCE;
    }

    public void saveSuplier(User user){
        editor.putString("supplier",user.getSupplier()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public User getSupplier(){
        User user = new User();
        user.setSupplier(preferences.getString("supplier",null));
        return user;
    }

}
