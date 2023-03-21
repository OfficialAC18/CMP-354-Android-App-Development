package com.example.lab_7_services;

import android.app.Application;
import android.content.Intent;

public class AppApplication extends Application {
    private Intent serviceIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceIntent = new Intent(this,AppService.class);
        startService(serviceIntent);
    }
}
