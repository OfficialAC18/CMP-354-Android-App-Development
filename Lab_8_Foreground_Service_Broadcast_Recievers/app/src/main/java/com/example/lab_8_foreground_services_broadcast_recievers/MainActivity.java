package com.example.lab_8_foreground_services_broadcast_recievers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cmrcl_arcrft;
    private Button heli;
    private Button dfns;
    private ArrayList<String> allLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmrcl_arcrft = (Button) findViewById(R.id.cmrcl_arcrft_btn);
        heli = (Button) findViewById(R.id.heli_btn);
        dfns = (Button) findViewById(R.id.dfns_btn);
        allLinks = new ArrayList<String>();

        allLinks.add("https://www.airbus.com/en/rss-all-feeds/15571?fid=29711"); //Commercial Aircraft Link
        allLinks.add("https://www.airbus.com/en/rss-all-feeds/15581?fid=29716"); //Helicopter Link
        allLinks.add("https://www.airbus.com/en/rss-all-feeds/15601?fid=29726"); //Space Link


        cmrcl_arcrft.setOnClickListener(this);
        heli.setOnClickListener(this);
        dfns.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cmrcl_arcrft_btn:
                Intent intent = new Intent(getApplicationContext(), ItemsActivity.class);
                intent.putExtra("Link", allLinks.get(0));
                startActivity(intent);
                break;
            case R.id.heli_btn:
                Intent intent1 = new Intent(getApplicationContext(), ItemsActivity.class);
                intent1.putExtra("Link", allLinks.get(1));
                startActivity(intent1);
                break;

            case R.id.dfns_btn:
                Intent intent2 = new Intent(getApplicationContext(), ItemsActivity.class);
                intent2.putExtra("Link", allLinks.get(2));
                startActivity(intent2);
                break;
        }


    }
}