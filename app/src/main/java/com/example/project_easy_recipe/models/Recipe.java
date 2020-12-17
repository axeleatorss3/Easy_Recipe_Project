package com.example.project_easy_recipe.models;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String title;
    private String image;

    public Recipe(int id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }


}
