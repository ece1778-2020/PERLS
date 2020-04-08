package com.ece1778.perls;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AgitationDetectorService extends Service implements SensorEventListener {

    private float xAccel, yAccel, zAccel;
    private float xPrevAccel, yPrevAccel, zPrevAccel;
    private double netAcceleration;
    private float accelThreshold = 1.0f, highIntensityThreshold = 3.0f;
    private int intensity=1;

    private NotificationHelper notificationHelper;

    boolean isFirstUpdate = true;
    boolean isShaking = false;

    Sensor accelerometer;
    SensorManager sensorManager;

    private long lastNotificationTimestamp = 0;
    private long frequency = 5; // maximum notify once a minute (changed to 5 seconds for demo)
    private long pollrate = 300; //in ms
    private long lastPollts = 0;
    @Override
    public void onCreate() {
        super.onCreate();

        notificationHelper = new NotificationHelper(getApplicationContext());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        lastPollts = System.currentTimeMillis();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (System.currentTimeMillis() - lastPollts < pollrate){
            return;
        }

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
        lastPollts = System.currentTimeMillis();
    }

    private void notifyForExercise() {
        if(netAcceleration > highIntensityThreshold){
            intensity = 2;
        }
        else {
            intensity = 1;
        }

        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Something wrong?", "Why not slow down with a PERLS activity?", intensity);
        notificationHelper.getManager().notify(1, nb.build());
        lastNotificationTimestamp = System.currentTimeMillis();

//        Intent intent = new Intent(this, ReflectionReview.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

    }

    private boolean isAccelerationChanged() {
        // Is there sufficient acceleration?

        float dX = xPrevAccel - xAccel;
        float dY = yPrevAccel - yAccel;
        float dZ = zPrevAccel - zAccel;
        netAcceleration = (Math.pow(dX,2)+Math.pow(dY,2)+Math.pow(dZ,2))/pollrate;
        Log.d("currentspeed", " " + netAcceleration );
        Log.d("currentspeed", " " + ( netAcceleration > accelThreshold) );

//        netAcceleration = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2));
        return netAcceleration > accelThreshold;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateAccelerationParameters(float xNewAccel, float yNewAccel, float zNewAccel) {
        if(isFirstUpdate){
            xPrevAccel = xNewAccel;
            yPrevAccel = yNewAccel;
            zPrevAccel = zNewAccel;
            isFirstUpdate = false;
        } else {
            xPrevAccel = xAccel;
            yPrevAccel = yAccel;
            zPrevAccel = zAccel;
        }
        xAccel = xNewAccel;
        yAccel = yNewAccel;
        zAccel = zNewAccel;
    }


}
