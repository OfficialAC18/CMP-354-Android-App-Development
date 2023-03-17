package com.example.lab_6_rss_feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ItemActivity extends AppCompatActivity {

    private Intent intent;
    private RSSItem item;
    private String title;
    private String imageLink;
    private String description;
    private String link;

    private TextView itemTitle,itemDescription,itemLink;
    private ImageView itemImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        intent = getIntent();
        item = (RSSItem)intent.getSerializableExtra("feedItem");


        title = item.getTitle();
        imageLink = item.getImageLink();
        description = item.getDescription();
        link = "<a href='"+item.getLink()+"'> Read more on the Web </a>";

        new DownloadImageTask().execute(imageLink);

        itemTitle = (TextView) findViewById(R.id.itemTitle);
        itemDescription = (TextView) findViewById(R.id.itemDescription);
        itemLink = (TextView) findViewById(R.id.itemLink);
        itemImage = (ImageView) findViewById(R.id.itemImage);


        itemTitle.setText(title);
        itemDescription.setText(description);
        itemLink.setText(Html.fromHtml(link,Html.FROM_HTML_MODE_COMPACT));
        itemLink.setMovementMethod(LinkMovementMethod.getInstance());



    }

    private class DownloadImageTask extends AsyncTask<String,Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try{
                URL newURL = new URL(strings[0]);
                bmp = BitmapFactory.decodeStream(newURL.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            itemImage.setImageBitmap(result);
        }
    }


}