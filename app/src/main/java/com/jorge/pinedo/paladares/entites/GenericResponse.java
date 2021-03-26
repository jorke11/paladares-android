package com.jorge.pinedo.paladares.entites;

import com.squareup.moshi.Json;

public class GenericResponse {

    @Json(name="status")
    Boolean status;
    @Json(name="msg")
    String msg;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
