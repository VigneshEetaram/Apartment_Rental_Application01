package com.example.apartment_rental_application;

public class Model {
    String Image;
    String Title;
    String Price;
    String Description;
    String Id;

    public Model() {
    }

    public Model(String image, String name, String price, String description, String id) {
        Image = image;
        Price = price;
        Title = name;
        Description = description;
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public Model(String description) {
        Description = description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        Title = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
