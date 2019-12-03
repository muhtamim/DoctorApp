package com.example.muhtamimnahid.doctorapp;

/**
 * Created by Abdullah Al Rifat on 08-Apr-18.
 */

public class food_model
{
    private int itemId;
    private String imageUrl;
    private String Name;
    private String Description;

    public food_model() {
    }

    public food_model(int itemId, String imageUrl, String name, String description) {
        this.itemId = itemId;
        this.imageUrl = imageUrl;
        Name = name;
        Description = description;
    }

    public food_model(String imageUrl, String name, String description) {
        this.imageUrl = imageUrl;
        Name = name;
        Description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
