package com.ece1778.perls;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import java.util.UUID;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class NotificationHelper  extends ContextWrapper {

    public static final String channelID = "exercisePromptID";
    public static final String channelName = "exercise prompt";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String SESSION_ID="sessionId";
    private static final String INTENSITY_ID="intensityId";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if(manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message, int intensity){
        Intent resutlIntent = new Intent(this, EmotionSelector.class);
        UUID session_uid = UUID.randomUUID();
        Long ts = System.currentTimeMillis();
        // Toast.makeText(MainActivity.this, "exercise id generated", Toast.LENGTH_SHORT).show();
        //intent.putExtra(EXERCISE_MESSAGE_ID, exerciseId);
        resutlIntent.putExtra(TIMESTAMP_ID, ts.toString());
        //Log.d("checking session id: ", session_uid.toString());
        resutlIntent.putExtra(SESSION_ID, session_uid.toString());
        resutlIntent.putExtra(INTENSITY_ID, intensity);
        PendingIntent intent = PendingIntent.getActivity(this, 1, resutlIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_badmood)
                .setAutoCancel(true)
                .setContentIntent(intent);
    }
}
