package com.jorge.pinedo.paladares.entites;

import com.squareup.moshi.Json;

public class User {

    @Json(name="id")
    Integer id;
    @Json(name="name")
    String name;
    @Json(name="email")
    String email;

    @Json(name="supplier")
    String supplier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
