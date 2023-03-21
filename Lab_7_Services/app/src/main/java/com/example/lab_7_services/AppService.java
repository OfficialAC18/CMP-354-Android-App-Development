package com.example.lab_7_services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class AppService extends Service {

    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lab-7","Service Started");
        startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lab-7","Service Destroyed");
        stopTimer();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startTimer(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d("Lab-7","Blink Eyes");
                createNotification("Blink Eyes");
            }
        };

        //create and start timer
        timer = new Timer(true);
        int delay = 1000; //1 Second
        int interval = 1000; //1 Second
        timer.schedule(task,delay,interval);


    }

    private void stopTimer(){
        if(timer!=null){
            timer.cancel();
        }

    }

    private void createNotification(String text){
        Intent notificationIntent = new Intent(this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int flag = PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this, 0, notificationIntent, flag);

        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = text;
        CharSequence contentTitle = getText(R.string.app_name);

        //To add a timestamp to the notification
        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        String dateFormatted = formatter.format(date);
        CharSequence contentText = text + " @: " + dateFormatted;

        NotificationChannel notificationChannel =
                new NotificationChannel("Channel_ID", "My Notifications",
                        NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager)
                getSystemService(this.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);

        Notification notification = new NotificationCompat
                .Builder(this, "Channel_ID")
                .setSmallIcon(icon)
                .setTicker(tickerText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setChannelId("Channel_ID")
                .build();

        final int NOTIFICATION_ID = 1;
        manager.notify(NOTIFICATION_ID, notification);


    }

}
