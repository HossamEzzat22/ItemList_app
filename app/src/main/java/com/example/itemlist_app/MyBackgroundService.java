package com.example.itemlist_app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class MyBackgroundService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(() -> {
            Log.d("Tag", "service is running now");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("service not working");
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
