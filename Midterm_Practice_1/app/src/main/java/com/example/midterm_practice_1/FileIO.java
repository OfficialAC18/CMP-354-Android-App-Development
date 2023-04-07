package com.example.midterm_practice_1;

import android.content.Context;
import android.util.Log;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {
    private ArrayList<String> URLS,titles;
    private final String FILENAME = "news_feed";
    RSSFeed fullFeed;
    private String searchTerm;

    private Context context = null;
    private Date date;

    public FileIO(Context context, ArrayList<String> URL,ArrayList<String> titles,String searchTerm, Date date){
        this.context = context;
        this.URLS = URL;
        this.fullFeed = new RSSFeed();
        this.titles = titles;
        this.searchTerm = searchTerm;
        this.date = date;
    }

    public void downloadFile(){
        try{

            //Get the Output Stream


            for(int i = 0;i<URLS.size();++i) {

                FileOutputStream out =
                        context.openFileOutput(FILENAME+"_"+i+".xml", Context.MODE_PRIVATE);

                //Getting the URL
                URL url = new URL(URLS.get(i));

                //Get the Input Stream
                InputStream in = url.openStream();
                // Read Input and Write Output
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                while(bytesRead != -1){

                    out.write(buffer, 0, bytesRead);
                    bytesRead = in.read(buffer);
                }

                in.close();
                out.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RSSFeed append(RSSFeed fuLLfeed,RSSFeed feed,String title,String searchTerm,Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Log.d("Lab-6",searchTerm.toLowerCase());
        for(RSSItem item:feed.getAllItems()){
            if(item.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) && dateFormat.parse(item.getPubDateFormatted()).compareTo(date) >= 0){
                item.setTitle("[" + title + "]" + item.getTitle());
                fuLLfeed.addItem(item);
            }
        }
        Log.d("Midterm-1","Required Items appended");
        return fuLLfeed;
    };
    public RSSFeed readFile()  {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlreader = saxParser.getXMLReader();

            for (int i = 0; i< URLS.size();++i) {
                //Parse the file
                RSSFeedHandler handler = new RSSFeedHandler();
                xmlreader.setContentHandler(handler);

                //Read File
                FileInputStream in = context.openFileInput(FILENAME + "_" + i + ".xml");

                //Create InputSource from Data and then pass it to xmlreader
                InputSource is = new InputSource(in);
                xmlreader.parse(is);

                //Retrieve data from the feed handler and append to fullFeed
                fullFeed = append(fullFeed, handler.getFeed(),titles.get(i),searchTerm,date);
            }
            return fullFeed;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



}
