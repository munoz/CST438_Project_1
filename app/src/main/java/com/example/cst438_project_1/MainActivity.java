package com.example.cst438_project_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.example.cst438_project_1.db.AppDatabase;
import com.example.cst438_project_1.db.PokedexDAO;

import java.util.List;

public class   MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.cst438_project_1.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.cst438_project_1.PREFERENCES_KEY";

    private Button mButtonSearch;
    private Button mButtonView;

    private int mUserId = -1;
    private PokedexDAO mPokedexDAO;

    private SharedPreferences mPreferences = null;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireupDisplay();
        getDatabase();
        checkForUser();

    }

    private void wireupDisplay() {

        mButtonSearch = findViewById(R.id.buttonSearch);
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchActivity.intentFactory(getApplicationContext(), mUserId);
                startActivity(intent);
            }
        });
        mButtonView = findViewById(R.id.viewSavedButton);
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ListActivity.intentFactory(getApplicationContext(), mUserId);
                startActivity(intent);
            }
        });
    }

    private void checkForUser() {
        //do we have a user in the intent
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

        //do we have user in the preference
        if(mUserId != -1){
            return;
        }

        if(mPreferences == null) {
            getPrefs();
        }

        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }


        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);

    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    private void getDatabase() {
        mPokedexDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getPokedexDAO();
    }
    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}