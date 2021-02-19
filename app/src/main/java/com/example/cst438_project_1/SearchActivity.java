package com.example.cst438_project_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.example.cst438_project_1.db.AppDatabase;
import com.example.cst438_project_1.db.PokedexDAO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.cst438_project_1.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.cst438_project_1.PREFERENCES_KEY";
    private Button mButtonGo;
    private Button mSaveButton;
    private int mUserId;
    private User mUser;

    private Pokemon mPokemon;
    private PokedexDAO mPokedexDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getDatabase();

        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        mUser = mPokedexDAO.getUserByUserId(mUserId);


        mButtonGo =  findViewById(R.id.goButton);
        mButtonGo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                search();

            }
        });
        mSaveButton = findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }

    public void search()
    {
        String search;
        TextView results = (TextView) findViewById(R.id.Results);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPokeHolderApi jsonPlaceHolderApi = retrofit.create(jsonPokeHolderApi.class);

        TextView searchBar = (TextView) findViewById(R.id.searchResponse);
        search = searchBar.getText().toString();

        Call<Pokemon> call = jsonPlaceHolderApi.getPokemon(search);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (!response.isSuccessful()) {
                    results.setText("Code: " + response.code());
                    return;
                }
                Pokemon pokemonAPI = response.body();
                String content = "";
                content += "Pokemon: " + pokemonAPI.getPokeName() + "\n";
                content += "ID: " + pokemonAPI.getPokeId() + "\n";
                content += "Height: " + pokemonAPI.getHeight() + "\n";
                content += "Weight: " + pokemonAPI.getWeight() + "\n";
//                content += "Types: " + pokemon.getTypes() + "\n";
                results.append(content);
                mPokemon = pokemonAPI;



            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                results.setText(t.getMessage());
            }
        });
    }

    public void save(){
        mPokemon.setUserId(mUserId);
        mPokedexDAO.insert(mPokemon);
    }

    private void getDatabase() {
        mPokedexDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getPokedexDAO();

    }
}
