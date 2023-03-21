package com.example.lab_8_foreground_services_broadcast_recievers;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItem implements Serializable {

    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;
    private String imageLink = null;


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }




    private SimpleDateFormat dateInFormat =
            new SimpleDateFormat("EEE, dd/mm/yyyy - HH:mm");

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


