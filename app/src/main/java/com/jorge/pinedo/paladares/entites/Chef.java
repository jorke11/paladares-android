package com.jorge.pinedo.paladares.entites;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Chef implements Serializable {

    @Json(name="id")
    Integer id;

    @Json(name="business")
    String business;

    @Json(name="document")
    String document;

    @Json(name="url_image")
    String url_image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}

