package com.example.cst438_project_1;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438_project_1.db.AppDatabase;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = AppDatabase.POKEMON_TABLE)
public class Pokemon {
    @SerializedName("name")
    private String mPokeName;
    @SerializedName("height")
    private String mHeight;
    @SerializedName("weight")
    private String mWeight;
    //@SerializedName("types")
    //private String mTypes;

    @PrimaryKey(autoGenerate = true)
    private int mPokeDbId;

    private int mUserId;

    public int getPokeDbId() {
        return mPokeDbId;
    }

    public void setPokeDbId(int mPokeDbId) {
        this.mPokeDbId = mPokeDbId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    @SerializedName("id")
    private String mPokeId;


    public Pokemon() {
    }


    public void setPokeName(String mPokeName) {
        this.mPokeName = mPokeName;
    }

    public void setHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public void setWeight(String mWeight) {
        this.mWeight = mWeight;
    }

//    public void setTypes(String mTypes) {
//        this.mTypes = mTypes;
//    }

    public void setPokeId(String mPokeId) {
        this.mPokeId = mPokeId;
    }

    public String getPokeName() {
        return mPokeName;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getPokeId() {
        return mPokeId;
    }

//    public String getTypes() { return mTypes; }
}


