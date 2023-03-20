package com.example.lab_4_tip_calculator_with_menus;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText billAmt;
    private RadioGroup tipPercent;

    private CheckBox weekday_Chkbox;
    private CheckBox member_Chkbox;
    private CheckBox special_Chkbox;
    private TextView totalAmt;
    private TextView totalPer;
    private Button submit;
    private Menu menu;
    private int numPersons;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the Object
        billAmt = (EditText) findViewById(R.id.billAmt_val);
        tipPercent = (RadioGroup) findViewById(R.id.tipPercent_btn);
        weekday_Chkbox = (CheckBox) findViewById(R.id.weekday_Chkbox);
        member_Chkbox = (CheckBox) findViewById(R.id.member_Chkbox);
        special_Chkbox = (CheckBox) findViewById(R.id.special_Chkbox);
        totalAmt = (TextView) findViewById(R.id.totalAmt_val);
        totalPer = (TextView) findViewById(R.id.totalPer_val);




        //Add a listener to the button
        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(this);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Your code goes here
                            Intent intent = result.getData();
                            numPersons = intent.getIntExtra("Num Persons", 1);
                        }
                    }
                });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_menu:
                return true;
            case R.id.help_menu:
                Toast.makeText(this, "Help feature not implemented", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share_menu:
                Intent intent = new Intent(getApplicationContext(), Share_bill.class);
                someActivityResultLauncher.launch(intent);
                Log.d("Lab-4", "Execution Completed");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private String formatAmt(float amt){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(amt);
    }


    @Override
    public void onClick(View v) {
        float total_Amt = 0;
        float total_Per = 0;
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



        //Calculate total amount
        total_Amt = (1-totalTipPercent)*total_Amt;

        //Calculate Per Person Amount
        total_Per = total_Amt / numPersons;

        //Set text with new values
        totalAmt.setText(formatAmt(total_Amt));
        totalPer.setText(formatAmt(total_Per));







    }
}