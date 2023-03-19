package com.example.lab_5button_lesstipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private EditText billAmt;
    private RadioGroup tipPrcnt;
    private Spinner numPersons;
    private CheckBox member, weekday, special;
    private TextView totalAmt, totalPerPerson;
    private float totalDiscount;
    private float beforeDiscountAmt = 0;
    private final double MEMBER_DISCNT = 0.05, WEEKDAY_DISCNT = 0.02, SPECIAL_DISCNT = 0.03;
    private final double TEN_PRCNT = 0.1, FIFTEEN_PRCNT = 0.15, TWENTY_PRCNT = 0.2;
    private double prevChecked = 0;
    private ArrayList<String> allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize All Objects
        billAmt = (EditText) findViewById(R.id.billAmt_val);
        tipPrcnt = (RadioGroup) findViewById(R.id.tipPercent_btn);
        numPersons = (Spinner) findViewById(R.id.bill_spnr);
        member = (CheckBox) findViewById(R.id.member_Chkbox);
        weekday = (CheckBox) findViewById(R.id.weekday_Chkbox);
        special = (CheckBox) findViewById(R.id.special_Chkbox);
        totalAmt = (TextView) findViewById(R.id.totalAmt_val);
        totalPerPerson = (TextView) findViewById(R.id.totalPer_val);
        allItems = new ArrayList<String>();
        totalDiscount = 0;

        //Adding The List Items to an adapter, binding the adapter to the spinner
        allItems.add("1 Person");
        allItems.add("2 Persons");
        allItems.add("3 Persons");
        allItems.add("4 Persons");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                                                                allItems);
        numPersons.setAdapter(adapter);


        billAmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d("Lab-1",billAmt.getText().toString());
                    totalAmt.setText(formatAmt(Float.parseFloat(billAmt.getText().toString())));
                    beforeDiscountAmt = Float.parseFloat(billAmt.getText().toString());
                    totalPerPerson.setText(formatAmt(beforeDiscountAmt/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
            }
        });

        //Setting Listeners to Widget
        //Refer To Slides of Chapter-2 GUI and Events
        member.setOnCheckedChangeListener(this);
        weekday.setOnCheckedChangeListener(this);
        special.setOnCheckedChangeListener(this);
        tipPrcnt.setOnCheckedChangeListener(this);
        numPersons.setOnItemSelectedListener(this);
    }

    public String formatAmt(double amt){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(amt);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.member_Chkbox:
                if(isChecked) {
                    totalDiscount+=MEMBER_DISCNT;
                    Log.d("Lab-5",Float.toString(totalDiscount));
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
                else{
                    totalDiscount-=MEMBER_DISCNT;
                    Log.d("Lab-5",Float.toString(totalDiscount));
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
                break;
            case R.id.weekday_Chkbox:
                if(isChecked){
                    totalDiscount+=WEEKDAY_DISCNT;
                    Log.d("Lab-5",Float.toString(totalDiscount));
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
                else{
                    totalDiscount-=WEEKDAY_DISCNT;
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
                break;
            case R.id.special_Chkbox:
                if(isChecked){
                    totalDiscount+=SPECIAL_DISCNT;
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
                else{
                    totalDiscount-=SPECIAL_DISCNT;
                    totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                    totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.tenPrcnt_btn:
                totalDiscount-=prevChecked;
                totalDiscount+=TEN_PRCNT;
                totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                prevChecked = TEN_PRCNT;
                break;
            case R.id.fifteenPrcnt_btn:
                totalDiscount-=prevChecked;
                totalDiscount+=FIFTEEN_PRCNT;
                totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                prevChecked = FIFTEEN_PRCNT;
                break;
            case R.id.twentyPrcnt_btn:
                totalDiscount-=prevChecked;
                totalDiscount+=TWENTY_PRCNT;
                totalAmt.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/Float.parseFloat(numPersons.getSelectedItem().toString().substring(0,1))));
                prevChecked = TWENTY_PRCNT;
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                totalPerPerson.setText(formatAmt((1-totalDiscount)*beforeDiscountAmt));
                break;
            case 1:
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/2));
                break;
            case 2:
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/3));
                break;
            case 3:
                totalPerPerson.setText(formatAmt(((1-totalDiscount)*beforeDiscountAmt)/4));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}