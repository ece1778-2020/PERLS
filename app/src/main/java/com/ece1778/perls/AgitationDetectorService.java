package com.ece1778.perls;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.provider.Settings;

import java.sql.Time;
import java.sql.Timestamp;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.annotation.Nullable;

public class AgitationDetectorService extends Service implements SensorEventListener {

    private float xAccel, yAccel, zAccel;
    private float xPrevAccel, yPrevAccel, zPrevAccel;
    private float accelThreshold = 12.5f;

    private NotificationHelper notificationHelper;

    boolean isFirstUpdate = true;
    boolean isShaking = false;

    Sensor accelerometer;
    SensorManager sensorManager;

    private long lastNotificationTimestamp = 0;
    private long frequency = 120000; // maximum notify once a minute (changed to 5 seconds for demo)

    @Override
    public void onCreate() {
        super.onCreate();

        notificationHelper = new NotificationHelper(getApplicationContext());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        updateAccelerationParameters(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);

        if(!isShaking && isAccelerationChanged()){
            isShaking = true;
        }
        else if (isShaking && isAccelerationChanged()) {
            if((System.currentTimeMillis() - lastNotificationTimestamp) > frequency){
                notifyForExercise();
            }

        }
        else if (isShaking && !isAccelerationChanged()){
            isShaking = false;
        }
    }

    private void notifyForExercise() {
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Something wrong?", "Why not slow down with a PERLS activity?");
        notificationHelper.getManager().notify(1, nb.build());
        lastNotificationTimestamp = System.currentTimeMillis();

//        Intent intent = new Intent(this, ReflectionReview.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

    }

    private boolean isAccelerationChanged() {
        // Is there sufficient acceleration?

        float dX = Math.abs(xPrevAccel - xAccel);
        float dY = Math.abs(yPrevAccel - yAccel);
        float dZ = Math.abs(zPrevAccel - zAccel);
        return (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2)) > accelThreshold);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateAccelerationParameters(float xNewAccel, float yNewAccel, float zNewAccel) {
        if(isFirstUpdate){
            xPrevAccel = xNewAccel;
            yPrevAccel = yNewAccel;
            xPrevAccel = zNewAccel;
        } else {
            xPrevAccel = xAccel;
            yPrevAccel = yAccel;
            xPrevAccel = zAccel;
        }
        xAccel = xNewAccel;
        yAccel = yNewAccel;
        zAccel = zNewAccel;
    }


}
