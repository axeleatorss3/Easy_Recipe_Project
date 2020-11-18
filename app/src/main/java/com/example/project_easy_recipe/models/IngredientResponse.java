package com.example.project_easy_recipe.models;

import java.util.ArrayList;

public class IngredientResponse {
    private ArrayList<Ingredients> extendedIngredients;

    public ArrayList<Ingredients> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(ArrayList<Ingredients> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }
}
