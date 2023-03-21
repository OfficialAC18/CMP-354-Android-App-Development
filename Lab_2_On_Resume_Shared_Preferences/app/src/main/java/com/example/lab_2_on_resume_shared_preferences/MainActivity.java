package com.example.lab_2_on_resume_shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private TextView firstNum, op, secondNum, correct, wrong, total;
    private EditText ansText;
    private Button nextBtn;
    private char[] OP = {'+','-','*','/'};
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (TextView) findViewById(R.id.firstNum_txt);
        op = (TextView) findViewById(R.id.op_txt);
        secondNum = (TextView) findViewById(R.id.secondNum_txt);
        correct = (TextView) findViewById(R.id.correct_val);
        wrong = (TextView) findViewById(R.id.wrong_val);
        total = (TextView) findViewById(R.id.total_val);
        ansText = (EditText) findViewById(R.id.ans_txt);
        nextBtn = (Button) findViewById(R.id.next_btn);
        sharedPref = getSharedPreferences("SavedValues",MODE_PRIVATE);

        correct.setTextColor(Color.GREEN);
        wrong.setTextColor(Color.RED);
        total.setTextColor(Color.GRAY);

        firstNum.setText(Integer.toString(new Random().nextInt(20)));
        secondNum.setText(Integer.toString(new Random().nextInt(20)));
        op.setText(String.valueOf(OP[new Random().nextInt(4)]));

        nextBtn.setOnClickListener(this);
        ansText.setOnEditorActionListener(this);


    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("firstVal",firstNum.getText().toString());
        edit.putString("op",op.getText().toString());
        edit.putString("secondVal",secondNum.getText().toString());
        edit.putString("correct",correct.getText().toString());
        edit.putString("wrong",wrong.getText().toString());
        edit.putString("total",total.getText().toString());
        edit.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        firstNum.setText(sharedPref.getString("firstVal",Integer.toString(new Random().nextInt(20))));
        secondNum.setText(sharedPref.getString("secondVal",Integer.toString(new Random().nextInt(20))));
        op.setText(sharedPref.getString("op",String.valueOf(OP[new Random().nextInt(4)])));
        correct.setText(sharedPref.getString("correct","0"));
        wrong.setText(sharedPref.getString("wrong","0"));
        total.setText(sharedPref.getString("total","0"));
        super.onResume();
    }

    @Override
    public void onClick(View v) {
                op.setText(String.valueOf(OP[new Random().nextInt(4)]));
                firstNum.setText(Integer.toString(new Random().nextInt(20)));
                secondNum.setText(Integer.toString(new Random().nextInt(20)));
                ansText.getText().clear();
                ansText.setTextColor(Color.BLACK);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int first = Integer.parseInt(firstNum.getText().toString());
        int second = Integer.parseInt(secondNum.getText().toString());
        int totalCorrect = Integer.parseInt(correct.getText().toString());
        int totalWrong = Integer.parseInt(wrong.getText().toString());
        int totalAns = Integer.parseInt(total.getText().toString());
        int ans = 0;
        String oper = op.getText().toString();
        switch (oper){
            case "+":
                ans = first + second;
                totalAns++;
                if(ans == Integer.parseInt(ansText.getText().toString())){
                    totalCorrect++;
                    correct.setText(Integer.toString(totalCorrect));
                }
                else{
                    totalWrong++;
                    wrong.setText(Integer.toString(totalWrong));
                }
                total.setText(Integer.toString(totalAns));
                break;
            case "-":
                ans = first - second;
                totalAns++;
                if(ans == Integer.parseInt(ansText.getText().toString())){
                    totalCorrect++;
                    correct.setText(Integer.toString(totalCorrect));
                }
                else{
                    totalWrong++;
                    wrong.setText(Integer.toString(totalWrong));
                }
                total.setText(Integer.toString(totalAns));
                break;
            case "*":
                ans = first * second;
                totalAns++;
                if(ans == Integer.parseInt(ansText.getText().toString())){
                    totalCorrect++;
                    correct.setText(Integer.toString(totalCorrect));
                }
                else{
                    totalWrong++;
                    wrong.setText(Integer.toString(totalWrong));
                }
                total.setText(Integer.toString(totalAns));
                break;
            case "/":
                ans = first / second;
                totalAns++;
                if(ans == Integer.parseInt(ansText.getText().toString())){
                    totalCorrect++;
                    correct.setText(Integer.toString(totalCorrect));
                }
                else{
                    totalWrong++;
                    wrong.setText(Integer.toString(totalWrong));
                }
                total.setText(Integer.toString(totalAns));
                break;
        }
        return false;
    }
}