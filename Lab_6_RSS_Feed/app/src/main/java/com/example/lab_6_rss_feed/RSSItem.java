package com.example.lab_6_rss_feed;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItem {

    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;


    private SimpleDateFormat dateInFormat =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    private SimpleDateFormat dateOutFormat =
            new SimpleDateFormat("dd-MMM-yyyy EEEE hh:mm");


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    //Code additional to the standard getters and setters
    public String getPubDateFormatted(){
        try {
            if (pubDate != null) {
                Date date = dateInFormat.parse(pubDate.trim());
                String pubDateFormatted = dateOutFormat.format(date);
                return pubDateFormatted;
            } else
                return "No date found";
        }
            catch (ParseException e){
                Log.e ("Lab-6", pubDate.toString());
                return "Date format not supported";
            }
        }

    }


