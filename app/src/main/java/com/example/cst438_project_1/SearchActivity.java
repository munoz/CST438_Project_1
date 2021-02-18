package com.example.cst438_project_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.content.SharedPreferences;

import org.w3c.dom.Text;

import java.text.Normalizer;


public class SearchActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.cst438_project_1.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.cst438_project_1.PREFERENCES_KEY";
    private Button mButtonGo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner type = (Spinner) findViewById(R.id.typeResponse);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(myAdapter);

        mButtonGo =  findViewById(R.id.goButton);
        mButtonGo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                search();
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
        Spinner type = (Spinner) findViewById(R.id.typeResponse);
        TextView results = (TextView) findViewById(R.id.Results);

        TextView searchBar = (TextView) findViewById(R.id.searchResponse);
        if(searchBar.getText().toString().equals(""))
        {
            int location = type.getSelectedItemPosition();

            results.setText(location);
        }
        else
        {
            search = searchBar.getText().toString();
            results.setText(search);
        }

        String url = "https://pokeapi.co/api/v2/";


    }
}
