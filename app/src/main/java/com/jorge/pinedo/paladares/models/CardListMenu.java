package com.jorge.pinedo.paladares.models;

import java.io.Serializable;

public class CardListMenu implements Serializable {

    private String card_name;
    private String description;
    private String date;

    public CardListMenu(String card_name, String description, String date) {
        this.card_name = card_name;
        this.description = description;
        this.date = date;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

