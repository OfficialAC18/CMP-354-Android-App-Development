package com.example.app_2tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    //Creates an XML Document which stores that App values
    private SharedPreferences savedValues;
    Button inc,dec;
    EditText et_text;
    TextView tip,total;
    TextView percent;
    Float tipPercent;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();
        //editor.putString("BillAmount", billAmountEditText.getText.toString());
        //editor.putFloat("TipPercent", tipPercent);
        //editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void calculateAndDisplay(){
        //Get String to check if it's empty
        String billAmt = et_text.getText().toString();
        // Float variable to hold value
        float amt,tip_amt = 0,total_amt = 0;
        if(billAmt.equals("")){
            amt =  0;
        }
        else {
            amt = Float.parseFloat(billAmt);

        }

        //For now, we assume tip to constant at 15%
        tip_amt = amt * tipPercent;
        total_amt = amt + tip_amt;

        //Set the text for Tip and Total
        NumberFormat DollarAmt = NumberFormat.getCurrencyInstance(Locale.US);
        tip.setText(DollarAmt.format(tip_amt));
        total.setText(DollarAmt.format(total_amt));



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize the Shared Preferences XML Document
        setContentView(R.layout.activity_main_2);
        //Shared Preferences File
        savedValues = getSharedPreferences("Saved Values", MODE_PRIVATE);

        //Initialize Tip Percent to 15%
        tipPercent = 0.15f;


        //Initializing and setting action listener to the button
        inc = (Button) findViewById(R.id.incrPerbtn);
        dec = (Button) findViewById(R.id.decrPerbtn);
        inc.setOnClickListener(this);
        dec.setOnClickListener(this);

        //Initialize the TextViews
        tip = (TextView) findViewById(R.id.tip_txt);
        total = (TextView) findViewById(R.id.total_txt);
        percent = (TextView) findViewById(R.id.perc_txt);

        //Initializing and setting action listener to the editText
        et_text = (EditText) findViewById(R.id.et_text_amt);
        et_text.setOnEditorActionListener(this);


    }

    @Override
    public void onClick(View v) {
        NumberFormat perc = NumberFormat.getPercentInstance();
        Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.incrPerbtn:
                if(tipPercent.equals(0.19f)){
                    inc.setClickable(false);
                }
                tipPercent+=0.01f;
                percent.setText(perc.format(tipPercent));
                calculateAndDisplay();
                break;
            case R.id.decrPerbtn:
                if(tipPercent.equals(.06f)){
                    dec.setClickable(false);
                }
                tipPercent-=0.01f;
                percent.setText(perc.format(tipPercent));
                calculateAndDisplay();
                break;
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //Args:
        //TextView: Pass "this" to it
        //KeyEvent event: This lets the listener know if the button has been pressed or released etc.
        calculateAndDisplay();
        return false;
    }
}