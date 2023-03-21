package com.example.lab_8_foreground_services_broadcast_recievers;

import android.content.Context;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {
    private String URL;
    private final String FILENAME = "news_feed.xml";
    private Context context = null;

    public FileIO(Context context, String URL){
        this.context = context;
        this.URL = URL;
    }

    public void downloadFile(){
        try{
            //Getting the URL
            URL url = new URL(URL);

            //Get the Input Stream
            InputStream in = url.openStream();

            //Get the Output Stream
            FileOutputStream out =
                    context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            // Read Input and Write Output
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            while(bytesRead != -1){

                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }
            out.close();
            in.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RSSFeed readFile()  {
        try{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLReader xmlreader = saxParser.getXMLReader();


        //Parse the file
        RSSFeedHandler handler = new RSSFeedHandler();
        xmlreader.setContentHandler(handler);

        //Read File
        FileInputStream in = context.openFileInput(FILENAME);

        //Create InputSource from Data and then pass it to xmlreader
        InputSource is = new InputSource(in);
        xmlreader.parse(is);

        //Retrieve data from the feed handler
        RSSFeed feed = handler.getFeed();
        return feed;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }



}
