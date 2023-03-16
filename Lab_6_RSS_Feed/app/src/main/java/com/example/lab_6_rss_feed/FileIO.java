package com.example.lab_6_rss_feed;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIO {
    private String URL;
    private final String FILENAME = "news_feed.xml";
    private Context context = null;

    public FileIO(Context context, String URL ){
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
                out.write(bytesRead);
                bytesRead = in.read(buffer);
            }
            out.close();
            in.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
