package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class about_activity extends AppCompatActivity {

    TextView about_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about_tv = findViewById(R.id.about_tv);
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("CustomTipCalculator");
        alert.setMessage(about_tv.getText());
        alert.create().show();
    }


}