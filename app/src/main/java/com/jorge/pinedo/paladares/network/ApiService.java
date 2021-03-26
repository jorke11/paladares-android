package com.jorge.pinedo.paladares.network;

import com.jorge.pinedo.paladares.entites.AccessToken;
import com.jorge.pinedo.paladares.entites.ChefResponse;
import com.jorge.pinedo.paladares.entites.GenericResponse;
import com.jorge.pinedo.paladares.entites.ProductResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("name") String name, @Field("email") String email, @Field("password") String password,@Field("phone") String phone);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("email") String username, @Field("password") String password);


    @POST("social_auth")
    @FormUrlEncoded
    Call<AccessToken>  socialAuth(@Field("name") String name, @Field("email") String email, @Field("provider") String provider, @Field("provider_user_id") String providerUserId);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("products")
    Call<ProductResponse> getProducts();


    @POST("products")
    @FormUrlEncoded
    Call<GenericResponse> createProducts(@Field("title") String title,@Field("description") String desription,@Field("price") String price,@Field("url_image") String url_image);

    @GET("stakeholders")
    Call<ChefResponse> getStakeholders();

    @POST("stakeholders")
    @FormUrlEncoded
    Call<GenericResponse> createStakeholder(@Field("business") String title,@Field("document") String desription,@Field("phone") String price,@Field("url_image") String url_image);


}
