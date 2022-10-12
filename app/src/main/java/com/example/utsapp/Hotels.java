package com.example.utsapp;

import org.json.JSONArray;

public class Hotels {
    private final String name;
    private final String price;
    private final String image;
    private final String description;
    private final JSONArray fasilitas;

    public Hotels(String name, String price, String image, String description, JSONArray fasilitas){
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.fasilitas = fasilitas;
    }

    public String getName(){
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription(){ return description; }

    public JSONArray getFasilitas(){ return fasilitas; }
}
