package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class shareBill_Activity extends AppCompatActivity implements View.OnClickListener {

    Button shareBill_btn;
    EditText shareBill_et;
    float totalPeople;
    Intent sendDatatomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_bill);
        shareBill_btn = findViewById(R.id.shareBill_btn);
        shareBill_et = findViewById(R.id.shareBill_et);
        shareBill_btn.setOnClickListener(this);
        sendDatatomain = new Intent();
    }

    @Override
    public void onClick(View view) {
        sendDatatomain.putExtra("Number of Persons", Float.parseFloat(shareBill_et.getText().toString()));
        setResult(Activity.RESULT_OK, sendDatatomain);
        finish();

    }
}