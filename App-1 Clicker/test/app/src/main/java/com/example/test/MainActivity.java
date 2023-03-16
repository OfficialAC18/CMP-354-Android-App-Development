package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView count;
    int counter  = 0;
    Toast countMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the text view object
        count = findViewById(R.id.txt_count);

    }

    public void inc_count(View v){
        if(v.getId() == R.id.btn_inc_count){  //Not Required here, however, when using this function for multiple functions, will be used to distinguish
            // Increment counter and update the text view accordingly
            counter++;
            count.setText(Integer.toString(counter));

            //Display Toast message
            countMessage = Toast.makeText(this, "Counter Incremented",Toast.LENGTH_SHORT);
            countMessage.show();

        }
    }
}