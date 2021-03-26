package com.jorge.pinedo.paladares;

import android.content.SharedPreferences;
import android.util.Log;

import com.jorge.pinedo.paladares.entites.AccessToken;

public class SupplierManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static SupplierManager INSTANCE = null;
    public String business;
    public String type_food;
    public String address;
    public String is_supplier;

    private SupplierManager(SharedPreferences preferences){
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    public static synchronized SupplierManager getInstance(SharedPreferences preferences){
         if(INSTANCE == null){
            INSTANCE = new SupplierManager(preferences);
         }

         return INSTANCE;
    }



    public String getBusiness() {
        business = preferences.getString("BUSINESS","");
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
        editor.putString("BUSINESS",business).commit();
    }

    public String getTypeFood() {
        type_food = preferences.getString("TYPE_FOOD","");
        return type_food;
    }

    public void setTypeFood(String type_food) {
        this.type_food = business;
        editor.putString("TYPE_FOOD",type_food).commit();
    }

    public String getAddress() {
        address = preferences.getString("TYPE_FOOD","");
        return address;
    }

    public String getIs_supplier() {
        is_supplier = preferences.getString("IS_SUPPLIER","");
        return is_supplier;
    }

    public void setIs_supplier(String is_supplier) {
        editor.putString("IS_SUPPLIER",is_supplier).commit();
        this.is_supplier = is_supplier;
    }

    public void setAddress(String address) {
        this.address = address;
        editor.putString("ADDRESS",address).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }
}
