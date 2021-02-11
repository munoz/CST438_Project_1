package com.example.cst438_project_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface jsonPokeHolderApi {

    @GET("pokemon/{name}")
    Call<List<Pokemon>> getPokemon(@Path("name") String pokemonName);

    @GET("pokemon/{id}")
    Call<List<Pokemon>> getPokemon(@Path("id") int pokemonId);
}
