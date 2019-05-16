package com.example.categoriesactivity;

import java.io.Serializable;

public class MenuItem implements Serializable{

    // instance variables
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    // constructor
    public MenuItem(String name, String description, String imageUrl, double price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
