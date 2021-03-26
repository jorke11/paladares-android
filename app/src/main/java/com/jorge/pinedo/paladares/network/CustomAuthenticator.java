package com.jorge.pinedo.paladares.network;

import android.support.annotation.Nullable;

import com.jorge.pinedo.paladares.TokenManager;
import com.jorge.pinedo.paladares.entites.AccessToken;


import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class CustomAuthenticator implements Authenticator {

    private TokenManager tokenManager;
    private static CustomAuthenticator INSTANCE;

    private CustomAuthenticator(TokenManager tokenManager){
        this.tokenManager=tokenManager;
    }

    static synchronized CustomAuthenticator getInstance(TokenManager tokenManager){
        if(INSTANCE==null){
            INSTANCE=new CustomAuthenticator(tokenManager);
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException{

        if(responseCount(response)>=2){
            return null;
        }

        AccessToken accessToken=tokenManager.getToken();

        ApiService apiService=RetrofitBuilder.createService(ApiService.class);
        Call<AccessToken> call=apiService.refresh(accessToken.getRefresh_token());

        retrofit2.Response<AccessToken> res = call.execute();

        if(res.isSuccessful()){
            AccessToken newToken=res.body();
            tokenManager.saveToken(newToken);

            return response.request().newBuilder().header("Authorization","Bearer "+res.body().getAccess_token()).build();
        }else{
            return null;
        }
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

}
