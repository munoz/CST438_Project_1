package com.example.cst438_project_1;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.cst438_project_1.db.AppDatabase;
import com.example.cst438_project_1.db.PokedexDAO;
import com.example.cst438_project_1.jsonPokeHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        PokedexDAO testPokeDAO = Room.databaseBuilder(appContext, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getPokedexDAO();

        /**
         * Test for checking if a user is inserted and retrieved from the database.
         */
        User user = new User("myusername", "mypassword");
        testPokeDAO.insert(user);
        User user2 = testPokeDAO.getUserByUsername(user.getUserName());
        assertEquals(user, user2);

        /**
         * Test for asserting that users with different username/passwords are not equal.
         */
        User user3 = new User("tim", "Imtim");
        assertNotEquals(user, user3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPokeHolderApi jsonPlaceHolderApi = retrofit.create(jsonPokeHolderApi.class);
        Call<Pokemon> bulb = jsonPlaceHolderApi.getPokemon(1);
        bulb.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                /**
                 * Test for retrieval of pokemon from the api.
                 */
                Pokemon testObj = response.body();
                Pokemon poke = new Pokemon("bulbasaur", "7", "69", "1");
                assertEquals(poke, testObj);

                /**
                 * Test for different assigned user ids to objects.
                 */
                testObj.setUserId(user2.getUserId());
                poke.setUserId(user3.getUserId());
                assertNotEquals(poke, testObj);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        assertEquals("com.example.cst438_project_1", appContext.getPackageName());
    }

}