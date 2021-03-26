package com.jorge.pinedo.paladares.entites;

import com.squareup.moshi.Json;

public class Product {

    @Json(name="id")
    Integer id;
    @Json(name="title")
    String title;
    @Json(name="description")
    String description;
    @Json(name="price")
    String price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
