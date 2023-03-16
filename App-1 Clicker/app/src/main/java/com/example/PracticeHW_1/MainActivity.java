package com.example.PracticeHW_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    //Initializing the Toast Message
    //Initializing the button and Counter View
    final Button increment_btn = (Button) findViewById(R.id.btn_increm_count);
    final TextView counter_view = (TextView) findViewById(R.id.txt_count);

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // Like the main program for Mobile Apps
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // This essentially provides which view we are in currently
    }

    public void IncrCounter(View view){
        if(view.getId() == R.id.btn_increm_count){ // Not required here, but could be required when multiple buttons use a single listener
            //Increment counter and update the view with the new value for counter
            counter++;
            counter_view.setText(Integer.toString(counter));

            //Display Toast Message after incrementing
            Toast.makeText(this, "Counter Incremented", Toast.LENGTH_SHORT).show();


        }
    }

}