package com.borrowbuddy.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String CHANNEL_ID = "BorrowBuddy_Channel";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        String title = "BorrowBuddy";
        String message = "New notification";


        if(remoteMessage.getNotification() != null){

            title = remoteMessage.getNotification().getTitle();

            message = remoteMessage.getNotification().getBody();

        }


        showNotification(title, message);
    }
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        System.out.println("FCM TOKEN = " + token);
    }
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseMessaging.getInstance()
                .getToken()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        System.out.println("FCM TOKEN = " + task.getResult());
                    }

                });
    }

    private void showNotification(String title, String message){


        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "BorrowBuddy Notifications",
                            NotificationManager.IMPORTANCE_HIGH
                    );


            manager.createNotificationChannel(channel);
        }



        Intent intent =
                new Intent(this, MainActivity.class);


        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                );



        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        this,
                        CHANNEL_ID
                )
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);



        manager.notify(
                1,
                builder.build()
        );

    }
}