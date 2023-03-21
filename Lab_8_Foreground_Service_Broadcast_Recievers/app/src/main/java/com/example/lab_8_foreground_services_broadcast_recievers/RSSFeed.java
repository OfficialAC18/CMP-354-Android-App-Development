package com.example.lab_8_foreground_services_broadcast_recievers;

import android.annotation.SuppressLint;

import androidx.appcompat.app.WindowDecorActionBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class RSSFeed {
    private String title = null;
    private String pubDate = null;
    private ArrayList<RSSItem> items;
    private SimpleDateFormat dateInFormat
            = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public RSSFeed() {items = new ArrayList<RSSItem>();}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    //To check if the new publication is any different
    public long getPubDateMillis(){
        try{
            Date date = dateInFormat.parse(pubDate.trim());
            return date.getTime();
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }

    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ArrayList<RSSItem> getAllItems() {
        return items;
    }

    public RSSItem getItem(int index){
        return items.get(index);
    }

    public int addItem(RSSItem item){
        items.add(item);
        return items.size();
    }




}
