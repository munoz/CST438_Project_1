package com.example.cst438_project_1.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst438_project_1.User;
import com.example.cst438_project_1.Pokemon;

import java.util.List;

@Dao
public interface PokedexDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + "  WHERE mUserName = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + "  WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Insert
    void insert(Pokemon... pokemons);

    @Update
    void update(Pokemon... pokemons);

    @Delete
    void delete(Pokemon pokemon);

    @Query("SELECT * FROM " + AppDatabase.POKEMON_TABLE)
    List<Pokemon> getAllPokemons();

    @Query("SELECT * FROM " + AppDatabase.POKEMON_TABLE + "  WHERE mUserId = :userId")
    List<Pokemon> getPokemonByUserId(int userId);

}
