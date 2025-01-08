package com.example.itemlist_app;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class CounterService extends Service {

    private static final String CHANNEL_ID = "counter_service_channel";
    private int counter = 0;
    private boolean isRunning = true;
    private Handler handler;



    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && "STOP_SERVICE".equals(intent.getAction())) {
            stopSelf();
            return START_NOT_STICKY;
        }

        createNotificationChannel();
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        startForeground(1, createNotification(counter));
        handler = new Handler();
        startCounter();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startCounter() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    counter++;
                    updateNotification(counter);
                    handler.postDelayed(this, 15000);
                }
            }
        }, 15000);
    }
    private Notification createNotification(int counterValue) {
        Intent stopIntent = new Intent(this, CounterService.class);
        Intent goToIntent = new Intent(this, MainActivity.class);

        stopIntent.setAction("STOP_SERVICE");
        PendingIntent stopPendingIntent = PendingIntent.getService(
                this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        PendingIntent goToPendingIntent = PendingIntent.getActivity(
                this, 0, goToIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Counter Service")
                .setContentText("Counter: " + counterValue)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.ic_launcher_foreground, "Stop", stopPendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "View list",goToPendingIntent )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
    }

    private void updateNotification(int counterValue) {
        Notification notification = createNotification(counterValue);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    private void createNotificationChannel() {
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Counter Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
        }
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
    }
}





