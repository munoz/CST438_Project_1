package com.example.cst438_project_1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cst438_project_1.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "POKEDEX_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract PokedexDAO getPokedexDAO();

}
