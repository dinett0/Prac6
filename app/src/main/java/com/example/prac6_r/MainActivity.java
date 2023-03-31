package com.example.prac6_r;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID="channel_id";
    private final String CHANNEL_NAME="Channel";
    private final String CHANNEL_DESC="Notification Channel";
    private static final int notificationId = 0;
    String notificationText = "Notification Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CHECK IF OREO
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //CHANNEL OBJECT
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        //CREATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        //CONFIGURATION
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(getString(R.string.notification_title));
        builder.setContentText(notificationText);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //ISSUE NOTIFICATION
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
    }
}