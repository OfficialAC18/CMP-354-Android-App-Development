package com.example.lab_7_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Menu menu;
    private Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(
                R.menu.servicemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        serviceIntent = new Intent(this,AppService.class);
        switch (item.getItemId()){

            case R.id.startService_menu:
                startService(serviceIntent);
                return true;
            case R.id.stopService_menu:
                stopService(serviceIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}