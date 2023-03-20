package com.example.lab_4_tip_calculator_with_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Share_bill extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private EditText numPersons;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_bill);

        numPersons = (EditText) findViewById(R.id.numPersons_txt);
        submit = (Button) findViewById(R.id.numPersons_btn);

        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        intent = getIntent();
        int numP = Integer.parseInt(numPersons.getText().toString());
        intent.putExtra("Num Persons",numP);
        //setResult(0,intent);
        finish();
    }
}