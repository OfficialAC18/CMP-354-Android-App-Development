package com.example.lab_6_rss_feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class ItemsActivity extends AppCompatActivity {

    private RSSFeed feed;

    private String url;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Intent intent = getIntent();



        //Getting the URL for the respective RSS Feed
        url = intent.getStringExtra("Link");

        new DownloadFeed().execute(url);
    }

    class DownloadFeed extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];



            return null;
        }

        @Override
        protected void onPostExecute(String result){
            //Context context = ItemsActivity.this;
            Log.d("Lab-6","Feed Downloaded");
        }
    }
}