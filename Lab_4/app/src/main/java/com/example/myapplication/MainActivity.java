package com.example.myapplication;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText billAmount;
    TextView totalAmount,totalPerPerson;

    TextView about_tv;
    private Button submit;
    private RadioButton tenPerc,fifPerc,twentPerc;
    private CheckBox memberBox, weekdayBox,specialBox;
    private float totalAmnt, billAmnt, totalPeople;
    SharedPreferences saved;
    private ActivityResultLauncher<Intent> IntentResultLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        billAmount = (EditText) findViewById(R.id.billAmt_val);
        totalAmount = (TextView) findViewById(R.id.totalAmountValue);
        totalPerPerson = (TextView) findViewById(R.id.totalPerPersonValue);
        submit = (Button) findViewById(R.id.submit_btn);
        tenPerc = (RadioButton) findViewById(R.id.tenPercent_btn);
        fifPerc = (RadioButton) findViewById(R.id.fifteenPercent_btn);
        twentPerc = (RadioButton) findViewById(R.id.twentyPercent_btn);
        memberBox = (CheckBox) findViewById(R.id.member_chkbox);
        weekdayBox = (CheckBox) findViewById(R.id.weekday_chkbox);
        specialBox = (CheckBox) findViewById(R.id.special_chkbox);
        totalPeople = 1;

        submit.setOnClickListener(this);
        IntentResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            totalPeople = intent.getFloatExtra("Number of Persons",1);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        totalAmnt = 0;
        billAmnt = Float.parseFloat(billAmount.getText().toString());
        if(tenPerc.isChecked()){
            totalAmnt+= billAmnt+0.10*billAmnt;

        } else if (fifPerc.isChecked()) {
            totalAmnt+=billAmnt+0.15*billAmnt;
        } else if (twentPerc.isChecked()) {
            totalAmnt+=billAmnt+0.2*billAmnt;
        }

        if(memberBox.isChecked()){
            totalAmnt-=0.05*totalAmnt;
        } if (weekdayBox.isChecked()) {
            totalAmnt-=totalAmnt*0.02;
        }if (specialBox.isChecked()) {
            totalAmnt-=totalAmnt*0.03;
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        totalAmount.setText(currency.format(totalAmnt));
        totalPerPerson.setText(currency.format(totalAmnt/totalPeople));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.help_menuItem:
                Toast.makeText(this,"Help feature has not been implemented",Toast.LENGTH_LONG).show();
                return true;
            case R.id.about_menuItem:
                Intent intent = new Intent(this, about_activity.class);
                startActivity(intent);
                return true;
            case R.id.shareBill_menuitem:
                intent = new Intent(this, shareBill_Activity.class);
                IntentResultLauncher.launch(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
