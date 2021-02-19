package com.example.cst438_project_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cst438_project_1.db.AppDatabase;
import com.example.cst438_project_1.db.PokedexDAO;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.cst438_project_1.userIdKey";

    private int mUserId;

    private TextView mPokemonList;

    private PokedexDAO mPokedexDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mPokemonList = (TextView) findViewById(R.id.pokemonList);
        getDatabase();
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        List<Pokemon> listPokemon = mPokedexDAO.getPokemonByUserId(mUserId);
        for (Pokemon pokemon: listPokemon){
            String content = "";
            content += "Name: " + pokemon.getPokeName() + "\n";
            content += "Id: " + pokemon.getPokeId() + "\n";
            content += "Height: " + pokemon.getHeight() + "\n";
            content += "Weight: " + pokemon.getWeight() + "\n\n";
            mPokemonList.append(content);
        }

    }

    private void getDatabase() {
        mPokedexDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getPokedexDAO();

    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}