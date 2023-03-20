package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

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

        //Populating Spinner
        ArrayList<String> persons = new ArrayList<String>();
        persons.add("1 person");
        persons.add("2 persons");
        persons.add("3 persons");
        persons.add("4 persons");

        //Attaching to adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                persons);

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        //Add a listener to the button
        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(this);



    }

    private String formatAmt(float amt){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(amt);
    }


    @Override
    public void onClick(View v) {
        float total_Amt = 0;
        float total_Per = 0;
        int numPeople = 1;
        float totalTipPercent = 0;

        //Get Entered Amount
        total_Amt = Float.parseFloat(billAmt.getText().toString());

        //Calculating the tip percent
        switch (tipPercent.getCheckedRadioButtonId()){
            case R.id.tenPrcnt_btn:
                totalTipPercent-=0.1;
                break;
            case R.id.fifteenPrcnt_btn:
                totalTipPercent-=0.15;
                break;
            case R.id.twentyPrcnt_btn:
                totalTipPercent-=0.2;
                break;
        }

        //Calculating discount due to member, weekday or special
        if(member_Chkbox.isChecked()){
            totalTipPercent+=0.05;
        }
        if(weekday_Chkbox.isChecked()){
            totalTipPercent+=0.02;
        }

        if(special_Chkbox.isChecked()){
            totalTipPercent+=0.03;
        }

        //Get Number of people
        Log.d("Lab-3",Integer.toString(spin.getSelectedItemPosition()));
        switch (spin.getSelectedItemPosition()){
            case 0:
                numPeople = 1;
                break;
            case 1:
                numPeople = 2;
                break;
            case 2:
                numPeople = 3;
                break;
            case 3:
                numPeople = 4;
        }

        //Calculate total amount
        total_Amt = (1-totalTipPercent)*total_Amt;

        //Calculate Per Person Amount
        total_Per = total_Amt / numPeople;

        //Set text with new values
        totalAmt.setText(formatAmt(total_Amt));
        totalPer.setText(formatAmt(total_Per));







    }
}