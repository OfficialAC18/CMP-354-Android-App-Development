package com.example.lab_6_rss_feed;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSFeedHandler extends DefaultHandler {
    // This is basically the RSS Feed Parser

    private RSSFeed feed;
    private RSSItem item;

    // For Each Item in RSS Feed
    private boolean isTitle = false;
    private boolean isLink = false;
    private boolean isDescription = false;
    private boolean isImage = false;
    private boolean isPubDate = false;

    private boolean feedTitleRead = false;
    private boolean feedPubDateRead = false;
    private boolean initialFeedLinkRead = false;

    public void startDocument() throws SAXException {
            feed = new RSSFeed();
            item = new RSSItem();
    }


    public RSSFeed getFeed(){
        return feed;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        if(qName.equals("item")){
            item = new RSSItem();
        }
        else if(qName.equals("lastBuildDate")){
           feedPubDateRead = true;
        }
        else if(qName.equals("title")){
            isTitle = true;
        }
        else if(qName.equals("link")){
            isLink = true;
        }
        else if(qName.equals("description")){
            isDescription = true;
        }
        else if(qName.equals("image")){
            isImage = true;
        }
        else if(qName.equals("pubDate")){
            isPubDate = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("item")){
            feed.addItem(item);
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) throws SAXException {
        if(feedPubDateRead){
            feed.setPubDate(new String(buffer, start, length));
            feedPubDateRead = false;
            Log.d("Lab-6","Feed Date Read");
        }
        else if(isTitle){
            if(!feedTitleRead){
                feed.setTitle(new String(buffer, start, length));
                feedTitleRead = true;
                Log.d("Lab-6","Feed Title Read");
            }
            else{
                item.setTitle(new String(buffer, start, length));
                Log.d("Lab-6","Item Title Read");
            }
            isTitle = false;
        }
        else if(isLink) {
            if (!initialFeedLinkRead) {
                initialFeedLinkRead = true;
            } else {
                item.setLink(new String(buffer, start, length));
                Log.d("Lab-6","Item Link Read");
            }
            isLink = false;
        }
        else if(isDescription){
                item.setDescription(new String(buffer, start, length));
                isDescription = false;
                Log.d("Lab-6","Item Description Read");
        }
        else if(isImage){
            item.setImageLink(new String(buffer, start, length));
            isImage = false;
            Log.d("Lab-6","Item Image Read");
        }
        else if(isPubDate){
            item.setPubDate(new String(buffer, start, length));
            isPubDate = false;
            Log.d("Lab-6","Item PubDate Read");
        }

    }
}


