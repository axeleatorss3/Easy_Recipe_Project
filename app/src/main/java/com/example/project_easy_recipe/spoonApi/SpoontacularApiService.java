package com.example.project_easy_recipe.spoonApi;

import com.example.project_easy_recipe.models.IngredientResponse;
import com.example.project_easy_recipe.models.Ingredients;
import com.example.project_easy_recipe.models.RecipeDetail;
import com.example.project_easy_recipe.models.SpoontacularRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpoontacularApiService {
    @GET("recipes/complexSearch?")
    Call<SpoontacularRespuesta> obtenerReceta(
            @Query(value = "apiKey") String apikey,
            @Query(value = "query") String query
    );
    @GET("recipes/{id}/information?apiKey=65628ebfbf434f2c8615a77f27fd0707&includeNutrition=false")
    public Call<RecipeDetail> find(@Path("id") String id);

    @GET("recipes/{id}/information?apiKey=65628ebfbf434f2c8615a77f27fd0707&includeNutrition=false")
    public Call<IngredientResponse> find2(@Path("id") String id);

}

