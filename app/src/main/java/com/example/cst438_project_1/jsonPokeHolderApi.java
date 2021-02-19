package com.example.cst438_project_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface jsonPokeHolderApi {

    @GET("pokemon/{pokeName}")
    Call<Pokemon> getPokemon(@Path("pokeName") String pokemonName);

    @GET("pokemon/{id}")
    Call<List<Pokemon>> getPokemon(@Path("id") int pokemonId);
}
