package com.example.project_easy_recipe.spoonApi;

import com.example.project_easy_recipe.models.SpoontacularRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoontacularApiService {
    @GET("recipes/complexSearch?")
    Call<SpoontacularRespuesta> obtenerReceta(
            @Query(value = "apiKey") String apikey,
            @Query(value = "query") String query
    );

}
