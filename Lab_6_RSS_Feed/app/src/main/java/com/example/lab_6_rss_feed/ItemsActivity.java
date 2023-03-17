package com.example.lab_6_rss_feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RSSFeed feed;
    private FileIO io;
    private TextView titleTextView;
    private ListView itemsListView;

    private String url;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Intent intent = getIntent();

        //Getting the URL for the respective RSS Feed
        url = intent.getStringExtra("Link");

        io = new FileIO(getApplicationContext(),url);

        titleTextView = (TextView)findViewById(R.id.FeedTitle);
        itemsListView = (ListView)findViewById(R.id.itemsList);
        itemsListView.setOnItemClickListener(this);

        new DownloadFeed().execute();
    }



    class DownloadFeed extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //Context context = ItemsActivity.this;
            Log.d("Lab-6","Feed Downloaded");
            new ReadFeed().execute();

        }
    }

    class ReadFeed extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            feed = io.readFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void Result){
            Log.d("Lab-6","Feed Read");
            updateDisplay();

        }

    }


    private void updateDisplay(){

        if(feed==null){
            titleTextView.setText("Unable to Get RSS Feed");
            return;
        }

        //Setting Feed Title
        titleTextView.setText(feed.getTitle());

        //Getting all the RSS Feed Items and Creating the HashMap to populate the ListView
        ArrayList<RSSItem>alLItems = feed.getAllItems();
        ArrayList<HashMap<String,String>>feedData = new ArrayList<HashMap<String, String>>();

        for(RSSItem feedItem:alLItems){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("date", feedItem.getPubDateFormatted());
            item.put("title", feedItem.getTitle());
            feedData.add(item);
        }
        String[] from = {"date","title"};
        int[] to = {R.id.Date, R.id.Title};

        SimpleAdapter simpleAdapter =
                new SimpleAdapter(getApplicationContext(),feedData,R.layout.listview_item, from, to);

        //Attach adapter to ListView
        itemsListView.setAdapter(simpleAdapter);
        Log.d("Lab-6","Feed Updated");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Get the item at the given position
        RSSItem item = feed.getItem(position);
        Intent intent = new Intent(this, ItemActivity.class);

        intent.putExtra("feedItem",item);
        startActivity(intent);
    }


}