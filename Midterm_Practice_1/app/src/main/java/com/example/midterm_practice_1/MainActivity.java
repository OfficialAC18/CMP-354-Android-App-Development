package com.example.midterm_practice_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String rssURLs[] = {
            "https://feeds.bbci.co.uk/news/rss.xml",
            "https://feeds.bbci.co.uk/news/world/rss.xml",
            "https://feeds.bbci.co.uk/news/business/rss.xml",
            "https://feeds.bbci.co.uk/news/technology/rss.xml"
    };
    private EditText searchTerm;
    private CheckBox top, world, biz, tech;
    private Button disp_btn;
    private RadioGroup numDays;

    String[] rss_topics = {"Top News", "World", "Business",  "Technology"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTerm = (EditText) findViewById(R.id.search_term_txt);
        top = (CheckBox) findViewById(R.id.top_news_box);
        world = (CheckBox) findViewById(R.id.world_news_box);
        biz = (CheckBox) findViewById(R.id.biz_news_box);
        tech = (CheckBox) findViewById(R.id.tech_news_box);
        numDays = (RadioGroup) findViewById(R.id.num_days_btn);
        disp_btn = (Button) findViewById(R.id.disp_news_btn);


        numDays.check(((RadioButton)numDays.getChildAt(0)).getId());
        disp_btn.setOnClickListener(this);

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    public void onClick(View v) {
        if(!biz.isChecked() && !top.isChecked() && !world.isChecked() && !tech.isChecked()){
            Toast.makeText(this, "At Least One Source Must Be Selected", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), ItemsActivity.class);
        int totalFiles = 0;
        ArrayList<String> allLinks = new ArrayList<String>();
        ArrayList<String> allTitles = new ArrayList<String>();
        if(top.isChecked()){
            allLinks.add(rssURLs[0]);
            allTitles.add(rss_topics[0]);
            totalFiles++;
        }

        if(world.isChecked()){
            allLinks.add(rssURLs[1]);
            allTitles.add(rss_topics[1]);
            totalFiles++;
        }

        if(biz.isChecked()){
            allLinks.add(rssURLs[2]);
            allTitles.add(rss_topics[2]);
            totalFiles++;
        }

        if(tech.isChecked()){
            allLinks.add(rssURLs[3]);
            allTitles.add(rss_topics[3]);
            totalFiles++;
        }
        Bundle links, titles;
        links = new Bundle();
        titles = new Bundle();
        links.putSerializable("ARRAYLIST",(Serializable) allLinks);
        titles.putSerializable("ARRAYLIST",(Serializable) allTitles);
        intent.putExtra("allLinks",links);
        intent.putExtra("allTitles",titles);
        intent.putExtra("numFiles",totalFiles);

        if(!isEmpty(searchTerm)){
            intent.putExtra("searchTerm",searchTerm.getText().toString());
        }
        else{
            intent.putExtra("searchTerm","");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date date;
        switch (numDays.getCheckedRadioButtonId()){
            case R.id.one_day_btn:
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                date = calendar.getTime();
                intent.putExtra("date",date);
                break;
            case R.id.two_day_btn:
                calendar.add(Calendar.DAY_OF_MONTH, -2);
                date = calendar.getTime();
                intent.putExtra("date",date);
                break;
            case R.id.one_week_btn:
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                date = calendar.getTime();
                intent.putExtra("date",date);
                break;
        }


        startActivity(intent);

    }
}