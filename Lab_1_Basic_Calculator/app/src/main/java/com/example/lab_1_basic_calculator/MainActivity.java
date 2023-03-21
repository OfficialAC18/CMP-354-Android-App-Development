package com.example.lab_1_basic_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstNum, op, secondNum;
    private EditText ansText;
    private Button checkBtn,nextBtn;
    private char[] OP = {'+','-','*','/'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (TextView) findViewById(R.id.firstNum_txt);
        op = (TextView) findViewById(R.id.op_txt);
        secondNum = (TextView) findViewById(R.id.secondNum_txt);
        ansText = (EditText) findViewById(R.id.ans_txt);
        checkBtn = (Button) findViewById(R.id.check_btn);
        nextBtn = (Button) findViewById(R.id.next_btn);

        firstNum.setText(Integer.toString(new Random().nextInt(20)));
        secondNum.setText(Integer.toString(new Random().nextInt(20)));
        op.setText(String.valueOf(OP[new Random().nextInt(4)]));

        checkBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.check_btn:
                int first = Integer.parseInt(firstNum.getText().toString());
                int second = Integer.parseInt(secondNum.getText().toString());
                int ans = 0;
                String oper = op.getText().toString();
                switch (oper){
                    case "+":
                        ans = first + second;
                        if(ans == Integer.parseInt(ansText.getText().toString())){
                            ansText.setTextColor(Color.GREEN);
                        }
                        else{
                            ansText.setTextColor(Color.RED);
                        }
                        break;
                    case "-":
                        ans = first - second;
                        if(ans == Integer.parseInt(ansText.getText().toString())){
                            ansText.setTextColor(Color.GREEN);
                        }
                        else{
                            ansText.setTextColor(Color.RED);
                        }
                        break;
                    case "*":
                        ans = first * second;
                        if(ans == Integer.parseInt(ansText.getText().toString())){
                            ansText.setTextColor(Color.GREEN);
                        }
                        else{
                            ansText.setTextColor(Color.RED);
                        }
                        break;
                    case "/":
                        ans = first / second;
                        if(ans == Integer.parseInt(ansText.getText().toString())){
                            ansText.setTextColor(Color.GREEN);
                        }
                        else{
                            ansText.setTextColor(Color.RED);
                        }
                        break;
                }
                break;
            case R.id.next_btn:
                op.setText(String.valueOf(OP[new Random().nextInt(4)]));
                firstNum.setText(Integer.toString(new Random().nextInt(20)));
                secondNum.setText(Integer.toString(new Random().nextInt(20)));
                ansText.getText().clear();
                ansText.setTextColor(Color.BLACK);
                break;

        }

    }
}