package com.example.lab_10_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Lat_text;
    private EditText Long_text;
    private EditText Desc_text;
    private EditText search_text;
    private Button search_btn;
    private Button add_btn;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize The Objects
        Lat_text = (EditText) findViewById(R.id.latitudeEditText);
        Long_text = (EditText) findViewById(R.id.longiEditText);
        Desc_text = (EditText) findViewById(R.id.editTextDesc);
        search_text = (EditText) findViewById(R.id.editTextSearch);
        search_btn = (Button) findViewById(R.id.buttonSearch);
        add_btn = (Button) findViewById(R.id.buttonAdd);

        //Adding Listeners to the buttons
        search_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loc_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSearch){

        }
        else if(v.getId() == R.id.buttonAdd){

        }

    }
}