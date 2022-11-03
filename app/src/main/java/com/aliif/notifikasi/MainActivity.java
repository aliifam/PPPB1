package com.aliif.notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String channelnotif = "mychannel";
    String channelid = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText judul = findViewById(R.id.judul);
        EditText isi = findViewById(R.id.isi);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, channelid)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(judul.getText().toString())
                        .setContentText(isi.getText().toString());
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance =  NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(channelnotif, "example channel", importance);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    mBuilder.setChannelId(channelnotif);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
            }
        });
    }
}