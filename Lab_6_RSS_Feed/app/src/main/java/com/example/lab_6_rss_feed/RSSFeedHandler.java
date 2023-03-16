package com.example.lab_6_rss_feed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSFeedHandler extends DefaultHandler {
    // This is basically the RSS Feed Parser

    private RSSFeed feed;
    private RSSItem item;

    // For Each Item in RSS Feed
    private Boolean isTitle;
    private Boolean isLink;
    private Boolean isDescription;
    private Boolean isImage;
    private Boolean isPubDate;

    private Boolean feedTitleRead;
    private Boolean feedPubDateRead;
    private Boolean initialFeedLinkRead;

    public RSSFeedHandler(){
        feed = new RSSFeed();
        feedTitleRead = false;
        initialFeedLinkRead = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

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
        }
        else if(isTitle){
            if(!feedTitleRead){
                feed.setTitle(new String(buffer, start, length));
                feedTitleRead = true;
            }
            else{
                item.setTitle(new String(buffer, start, length));

            }
            isTitle = false;
        }
        else if(isLink) {
            if (!initialFeedLinkRead) {
                initialFeedLinkRead = true;
            } else {
                item.setLink(new String(buffer, start, length));
            }
            isLink = false;
        }
        else if(isDescription){
                item.setDescription(new String(buffer, start, length));
                isDescription = false;
            }
        else if(isImage){
            item.setImageLink(new String(buffer, start, length));
            isImage = false;
        }
        else if(isPubDate){
            item.setPubDate(new String(buffer, start, length));
            isPubDate = false;
        }

    }
}
