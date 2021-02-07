package com.example.cst438_project_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.example.cst438_project_1.db.AppDatabase;
import com.example.cst438_project_1.db.PokedexDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.cst438_project_1.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.cst438_project_1.PREFERENCES_KEY";

    private int mUserId = -1;
    private PokedexDAO mPokedexDAO;

    private SharedPreferences mPreferences = null;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();
        checkForUser();



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

        //do we have any users at all
        List<User> users = mPokedexDAO.getAllUsers();
        if(users.size() <= 0){
            User defaultUser = new User("daclink", "dac123");
            User altUser = new User("drew", "dac123");
            mPokedexDAO.insert(defaultUser);
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