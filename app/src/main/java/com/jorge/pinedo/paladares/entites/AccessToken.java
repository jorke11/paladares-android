package com.jorge.pinedo.paladares.entites;

import com.squareup.moshi.Json;

public class AccessToken {

    @Json(name="token_type")
    String tokenType;
    @Json(name="expire_in")
    String expireIn;
    @Json(name="access_token")
    String access_token;

    @Json(name="refresh_token")
    String refresh_token;


    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(String expireIn) {
        this.expireIn = expireIn;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "tokenType='" + tokenType + '\'' +
                ", expireIn='" + expireIn + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
