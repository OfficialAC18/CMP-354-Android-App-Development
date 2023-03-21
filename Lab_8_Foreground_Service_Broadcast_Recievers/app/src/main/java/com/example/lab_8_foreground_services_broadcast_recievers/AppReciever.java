package com.example.lab_8_foreground_services_broadcast_recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Lab-8","Boot Completed");

        //Start Service
        Intent service = new Intent(
                context, AppService.class);
        context.startService(service);
    }
}