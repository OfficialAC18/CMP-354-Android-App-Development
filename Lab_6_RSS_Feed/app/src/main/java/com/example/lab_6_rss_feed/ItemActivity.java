package com.example.lab_6_rss_feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ItemActivity extends AppCompatActivity {

    private Intent intent;
    private RSSItem item;
    private String title;
    private String imageLink;
    private String description;
    private String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        intent = getIntent();
        item = (RSSItem)intent.getSerializableExtra("feedItem");

        title = item.getTitle();
        imageLink = item.getImageLink();
        description = item.getDescription();
        link = item.getLink();

    }
}