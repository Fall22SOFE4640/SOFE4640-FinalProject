package com.example.final_project;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cars {

    private int year, price;
    private String make;
    private String model;
    private String description;


    public Cars(){

    }
    public Cars(int year, String make, String model, int price, String description) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
        this.description = description;
    }


    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
