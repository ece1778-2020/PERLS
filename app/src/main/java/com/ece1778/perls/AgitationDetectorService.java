package com.ece1778.perls;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class AgitationDetectorService extends Service implements SensorEventListener {

    private float xAccel, yAccel, zAccel;
    private float xPrevAccel, yPrevAccel, zPrevAccel;
    private float accelThreshold = 12.5f;

    boolean isFirstUpdate = true;
    boolean isShaking = false;

    Sensor accelerometer;
    SensorManager sensorManager;

    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
            notifyForExercise();
        }
        else if (isShaking && !isAccelerationChanged()){
            isShaking = false;
        }
    }

    private void notifyForExercise() {
        Intent intent = new Intent(this, ReflectionReview.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
