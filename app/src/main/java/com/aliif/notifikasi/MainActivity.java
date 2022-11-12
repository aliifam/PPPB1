package com.aliif.notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String channelnotif = "mychannel";
    String channelid = "default";

    private NotificationManager notificationManager;

    private Button button;
    private Button update;
    private Button cancel;
    private EditText judul;
    private EditText isi;

    private static final String NOTIFICATION_GUIDE_URL =
            "https://aliif.space";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.aliif.notifikasi.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION =
            "com.aliif.notifikasi.ACTION_CANCEL_NOTIFICATION";

    private final NotificationReceiver mReceiver = new NotificationReceiver();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        EditText judul = findViewById(R.id.judul);
        EditText isi = findViewById(R.id.isi);
        Button button = findViewById(R.id.button);
        Button update = findViewById(R.id.update);
        Button cancel = findViewById(R.id.cancel);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
        intentFilter.addAction(ACTION_CANCEL_NOTIFICATION);
        registerReceiver(mReceiver, intentFilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("UnspecifiedImmutableFlag") PendingIntent action = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, channelid)
                        .setSmallIcon(R.drawable.ic_baseline_android_24)
                        .setContentTitle(judul.getText().toString())
                        .setContentText(isi.getText().toString())
                        .setTicker("Small Text")
                        .setAutoCancel(true)
                        .setContentIntent(action);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance =  NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(channelnotif, "example channel", importance);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    mBuilder.setChannelId(channelnotif);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                notificationManager.notify(0, mBuilder.build());
                //Enables the update and cancel buttons but disables the "Notify Me!" button
                button.setEnabled(false);
                update.setEnabled(true);
                cancel.setEnabled(true);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });
    }

    private void updateNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        EditText judul = findViewById(R.id.judul);
        EditText isi = findViewById(R.id.isi);
        Button button = findViewById(R.id.button);
        Button update = findViewById(R.id.update);
        Button cancel = findViewById(R.id.cancel);

        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.img);

        //Sets up the pending intent that is delivered when the notification is clicked
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Sets up the pending intent to cancel the notification,
        // delivered when the user dismisses the notification
        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICATION);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent cancelPendingIntent = PendingIntent.getBroadcast
                (getApplicationContext(), 0, cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent associated with the Learn More notification action,
        //uses an implicit intent to go to the web.
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (getApplicationContext(), 0, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        //Build the updated notification
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(MainActivity.this, channelid)
                .setContentTitle(judul.getText().toString())
                .setContentText(isi.getText().toString())
                .setSmallIcon(R.drawable.ic_baseline_android_24)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setDeleteIntent(cancelPendingIntent)
                .addAction(R.drawable.ic_learn_more, getString(R.string.learn_more),
                        learnMorePendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle(getString(R.string.notification_updated)));

        button.setEnabled(false);
        update.setEnabled(false);
        cancel.setEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance =  NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelnotif, "example channel", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notifyBuilder.setChannelId(channelnotif);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Deliver the notification
        notificationManager.notify(0, notifyBuilder.build());
    }

    private void cancelNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        EditText judul = findViewById(R.id.judul);
        EditText isi = findViewById(R.id.isi);
        Button button = findViewById(R.id.button);
        Button update = findViewById(R.id.update);
        Button cancel = findViewById(R.id.cancel);

        //Cancel the notification
        notificationManager.cancel(0);

        //Resets the buttons
        button.setEnabled(true);
        update.setEnabled(false);
        cancel.setEnabled(false);
    }

    private class NotificationReceiver extends BroadcastReceiver {

        /**
         * Gets the action from the incoming broadcast intent and responds accordingly
         * @param context Context of the app when the broadcast is received.
         * @param intent The broadcast intent containing the action.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_CANCEL_NOTIFICATION:
                    cancelNotification();
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    updateNotification();
                    break;
            }
        }
    }
}