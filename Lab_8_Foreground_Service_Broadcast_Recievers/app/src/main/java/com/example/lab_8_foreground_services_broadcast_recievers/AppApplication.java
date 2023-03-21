package com.example.lab_8_foreground_services_broadcast_recievers;

import android.app.Application;
import android.content.Intent;

public class AppApplication extends Application {
    private Intent serviceIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceIntent = new Intent(this,AppService.class);
        startForegroundService(serviceIntent);
    }
}
