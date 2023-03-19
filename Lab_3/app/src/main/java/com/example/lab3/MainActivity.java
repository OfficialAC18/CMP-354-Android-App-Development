package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText billAmt;
    private RadioGroup tipPercent;
    private Spinner spin;
    private CheckBox weekday_Chkbox;
    private CheckBox member_Chkbox;
    private CheckBox special_Chkbox;
    private TextView totalAmt;
    private TextView totalPer;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the Object
        billAmt = (EditText) findViewById(R.id.billAmt_val);
        tipPercent = (RadioGroup) findViewById(R.id.tipPercent_btn);
        spin = (Spinner) findViewById(R.id.bill_spnr);
        weekday_Chkbox = (CheckBox) findViewById(R.id.weekday_Chkbox);
        member_Chkbox = (CheckBox) findViewById(R.id.member_Chkbox);
        special_Chkbox = (CheckBox) findViewById(R.id.special_Chkbox);
        totalAmt = (TextView) findViewById(R.id.totalAmt_val);
        totalPer = (TextView) findViewById(R.id.totalPer_val);

        //Add a listener to the button
        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        float total_Amt = 0;
        float total_Per = 0;
        tipPercent.getCheckedRadioButtonId();

    }
}